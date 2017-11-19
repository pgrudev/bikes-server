package pl.pgrudev.core;

import akka.actor.ActorSystem;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketFrameAggregator;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.SelfSignedCertificate;
import io.netty.util.internal.SystemPropertyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import pl.pgrudev.core.netty.HttpRequestHandler;
import pl.pgrudev.core.netty.WebSocketFrameHandler;

import javax.inject.Inject;
import java.util.Locale;

@Service
public final class WebsocketGateway {

    private static final Logger logger = LogManager.getLogger(WebsocketGateway.class);
    private final static String osName = SystemPropertyUtil.get("os.name").toLowerCase(Locale.UK).trim();
    private final static boolean linux = osName.startsWith("linux");

    //@Value("${core.websocket.path} ?: /BikeServer")
    @Value("/BikeServer")
    private String path;

    //@Value("${core.websocket.port} ?: 8090")
    @Value("8090")
    private int port;

    //@Value("${core.websocket.maxframesize} ?: 65536")
    @Value("65536")
    private int maxFrame;

    //@Value("${core.websocket.ssl} ?: false")
    @Value("false")
    private boolean ssl;

    @Inject
    private AutowireCapableBeanFactory beanFactory;

    @Inject
    private ActorSystem actorSystem;

    private EventLoopGroup parentGroup;
    private EventLoopGroup childGroup;

    public void start() throws InterruptedException {
        try {
            ChannelFuture f1 = startServer();
         //   ChannelFuture f2 = startTestServer();
            f1.sync();
           // f2.sync();
        } catch (Exception e) {
            logger.error("Could not start server", e);
        } finally {
            shutdownServer(null);
        }
    }

    private ChannelFuture startServer() throws Exception {
        final SslContext sslCtx;
        if (ssl) {
            SelfSignedCertificate ssc = new SelfSignedCertificate();
            sslCtx = SslContextBuilder.forServer(ssc.certificate(), ssc.privateKey()).build();
        } else {
            sslCtx = null;
        }

        parentGroup = getNativeEventLoopGroup(1);
        if (logger.isInfoEnabled()) {
            parentGroup.terminationFuture().addListener((future) -> {
                Throwable cause = future.cause();
                logger.info("Parent group has terminated" + (cause != null ? (": " + cause) : ""));
            });
        }

        childGroup = getNativeEventLoopGroup(0);
        if (logger.isInfoEnabled()) {
            childGroup.terminationFuture().addListener((future) -> {
                Throwable cause = future.cause();
                logger.info("Child group has terminated" + (cause != null ? (": " + cause) : ""));
            });
        }
        ServerBootstrap server = new ServerBootstrap();
        server.group(parentGroup, childGroup)
                .option(ChannelOption.SO_SNDBUF, 1048576)
                .childOption(ChannelOption.TCP_NODELAY, true)
                .channel(getNativeServerChannelClass())
                .handler(new LoggingHandler(getClass(), LogLevel.DEBUG))
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();
                        if (sslCtx != null) {
                            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
                        }
                        pipeline.addLast(new HttpServerCodec());
                        pipeline.addLast(new HttpObjectAggregator(65536));

                        pipeline.addLast(beanFactory.getBean("HttpRequestHandler", HttpRequestHandler.class));

                        pipeline.addLast(new WebSocketServerCompressionHandler());
                        pipeline.addLast(new WebSocketFrameAggregator(maxFrame));
                        pipeline.addLast(new WebSocketServerProtocolHandler(path, null, true, maxFrame));
                        pipeline.addLast(beanFactory.getBean("WebSocketFrameHandler", WebSocketFrameHandler.class));
                    }
                });

        actorSystem.registerOnTermination(() -> {
            shutdownServer("the System Actor has terminated");
        });

        // Start listening
        Channel channel = server.bind(port).sync().channel();


        logger.info("Netty (transport: {}) is listening on port " + port + " " + (ssl ? "with" : "without")
                        + " SSL (" + (ssl ? "https" : "http") + "://127.0.0.1:" + port + path + ")",
                () -> parentGroup.getClass().getSimpleName());
            /*logger.info("Tester is listening on port " + testPort + " " + (ssl ? "with" : "without") + " SSL (" + (
                    ssl ?
                            "https" :
                            "http") + "://127.0.0.1:" + testPort + ")");
        }*/

        return channel.closeFuture();
    }

    private EventLoopGroup getNativeEventLoopGroup(int threads) {
        if (linux) {
            return new EpollEventLoopGroup(threads);
        }
        return new NioEventLoopGroup(threads);
    }


    private ChannelFuture startTestServer() {

        return null;
    }

    private void shutdownServer(String cause) {
        if (!parentGroup.isShuttingDown()) {
            parentGroup.shutdownGracefully();
        }

        if (!childGroup.isShuttingDown()) {
            childGroup.shutdownGracefully();
        }
    }

    private Class<? extends ServerChannel> getNativeServerChannelClass() {
        if (linux) {
            return EpollServerSocketChannel.class;
        } else {
            return NioServerSocketChannel.class;
        }
    }
}
