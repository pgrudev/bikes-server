package pl.pgrudev.core.netty;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.AttributeKey;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Scope;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

import static pl.pgrudev.core.spring.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Scope("prototype")
public class HttpRequestHandler extends MessageToMessageDecoder<FullHttpRequest> {
    private static final Logger logger = LogManager.getLogger(HttpRequestHandler.class);
    private static AttributeKey<String> IPAddress = AttributeKey.valueOf("IP_ADDRESS");
    @Inject
    private ActorSystem actorSystem;

    @Override
    protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {
        try {
            String addr = Optional.ofNullable(msg.headers().get("x-forwarded-for")).orElse(ctx.channel().remoteAddress().toString());

            ctx.channel().attr(IPAddress).set(addr);
            System.out.println(ctx.channel().attr(HttpRequestHandler.IPAddress));
            logger.debug("Channel registered: New SessionActor starting in context {}", ctx);
            ActorRef ref = actorSystem.actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("websocketSessionActor",ctx).withDispatcher("cpeer-dispatcher"),
                    ctx.channel().id().asShortText());

        } catch (Exception e) {
            logger.error("Channel registered: Unable to start new SessionActor in context {}", ctx, e);
            ctx.close();
        }
        out.add(msg.retain());
    }
}
