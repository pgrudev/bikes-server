package pl.pgrudev.core.session;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.sun.org.apache.regexp.internal.RE;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.internal.tcnative.Buffer;
import org.springframework.context.annotation.Scope;
import scala.concurrent.Future;

import javax.inject.Named;

@Named("SessionActor")
public abstract class SessionActor extends AbstractActorWithStash {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public static ActorRef getSession(ActorSystem system, String session) {
        return system.actorFor("/user/" + session);
    }

    private ChannelHandlerContext ctx;

    public SessionActor(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    private Gson gson;
    public void preStart() throws Exception {
        logger.debug("Actor starting");
        this.gson = new Gson();
        super.preStart();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    logger.info("Received message: " + msg);
                    try {
                        Request request = gson.fromJson(msg, Request.class);
                        Response response = createResponse(request);
                        ctx.channel().writeAndFlush(new TextWebSocketFrame(gson.toJson(response)));
                    } catch (JsonSyntaxException e) {
                        logger.warning("Error in parsing message: " + msg);
                    }
                })
                .build();
    }

    private Response createResponse(Request request) {
        String[] response = new String[]{"Request was sent by", this.getSelf().toString()};
        return new Response(request, response, null);
    }

    public abstract Future<Object[]> handleRequest(final Request request);

    private void response(Future<Object[]> response) {
        response(response, null);
    }

    private void response(Future<Object[]> response, final Request req) {

        final ActorRef self = getSelf();
        final ActorRef sender = getSender();

        response.onComplete(new OnComplete<Object[]>() {

            @Override
            public void onComplete(Throwable failure, Object[] response) throws Throwable {
                self.tell(new Response(req, response, failure), sender);
            }

        }, context().dispatcher());
    }


    public void response(Object[] response, Request req, boolean completed) throws Exception {
        //create new Response object and send
    }


    private void response(Response resp, boolean completed) throws Exception {
        response(resp.getResponse(), resp.getRequest(), completed);
    }
}
