package pl.pgrudev.core.netty;

import io.netty.channel.ChannelHandlerContext;
import org.springframework.context.annotation.Scope;
import pl.pgrudev.core.session.Request;
import pl.pgrudev.core.session.SessionActor;
import scala.concurrent.Future;

import javax.inject.Named;

@Named("WebsocketSessionActor")
@Scope("prototype")
public class WebSocketSessionActor extends SessionActor {
    private ChannelHandlerContext ctx;

    public WebSocketSessionActor(ChannelHandlerContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public void postStop() {
        ctx.close();
        super.postStop();
    }

    @Override
    public Future<Object[]> handleRequest(final Request request){
        return null;
    }


}
