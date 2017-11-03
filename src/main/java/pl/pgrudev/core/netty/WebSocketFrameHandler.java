package pl.pgrudev.core.netty;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.pgrudev.core.session.SessionActor;

import javax.inject.Inject;

public class WebSocketFrameHandler extends SimpleChannelInboundHandler<WebSocketFrame> {
    private static final Logger logger = LogManager.getLogger(WebSocketFrameHandler.class);

    @Inject
    private ActorSystem actorSystem;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, WebSocketFrame msg) throws Exception {
        ActorRef ref = SessionActor.getSession(actorSystem, ctx.channel().id().asShortText());
        if (msg instanceof TextWebSocketFrame) {
            String message = ((TextWebSocketFrame) msg).text();
            logger.debug("New text message in context {} received: {}", ctx, message);

            ref.tell(message, ActorRef.noSender());
        } else {
            logger.error("Unsupported frame type {} in context {}", msg.getClass().getName(), ctx);
        }
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        ActorRef ref = SessionActor.getSession(actorSystem, ctx.channel().id().asShortText());
        if (!ref.isTerminated()) {
            logger.debug("Channel unregistered: Stopping SessionActor {} in context {}", ref, ctx);
            actorSystem.stop(ref);
        } else {
            logger.debug("Channel unregistered: SessionActor {} already terminated in context {}", ref, ctx);
        }

        super.channelUnregistered(ctx);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("Exception caught", cause);
        super.exceptionCaught(ctx, cause);
    }

}
