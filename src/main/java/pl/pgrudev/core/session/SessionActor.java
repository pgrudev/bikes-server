package pl.pgrudev.core.session;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.beans.factory.annotation.Autowired;
import pl.pgrudev.client.User;
import pl.pgrudev.repository.UserRepository;
import scala.concurrent.Future;

import javax.inject.Named;

@Named("SessionActor")
public abstract class SessionActor extends AbstractActorWithStash {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);
    private ChannelHandlerContext ctx;
    private Gson gson;
    private boolean loggedIn;
    private String login;
    private User user;


    @Autowired
    private UserRepository repository;

    public SessionActor(ChannelHandlerContext ctx) {
        this.ctx = ctx;
    }

    public static ActorRef getSession(ActorSystem system, String session) {
        return system.actorFor("/user/" + session);
    }

    @Override
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
                        send(response);
                    } catch (JsonSyntaxException e) {
                        logger.warning("Error in parsing message: " + msg);
                    }
                })
                .build();
    }

    public abstract Future<Object> handleRequest(final Request request);

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

    private Response createResponse(Request request) {
        String[] response = new String[]{"Request was sent by", this.getSelf().toString()};
        return new Response(request, response, null);
    }

    public void response(Request req, Object response, boolean completed) throws Exception {
        send(createResponse(req));
    }

    private void response(Response resp, boolean completed) throws Exception {
       // response(resp.getRequest(), resp.getResponse(), completed);
    }

    public void send(Response response) {
       ctx.channel().writeAndFlush(new TextWebSocketFrame(gson.toJson(response)));
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
