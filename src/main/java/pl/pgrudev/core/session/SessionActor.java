package pl.pgrudev.core.session;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import pl.pgrudev.client.Command;
import scala.concurrent.Future;

import javax.inject.Named;

@Named
public abstract class SessionActor extends AbstractActorWithStash {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public static ActorRef getSession(ActorSystem system, String session) {
        return system.actorFor("/user/" + session);
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(Request.class, req -> response(handleRequest(req.getCommand())))
                .match(Response.class, resp -> response(resp, true))
                .build();
    }

    private Future<Object[]> handleRequest(final Command cmd ) {
        //api calls go here

        return null;
    }

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


    public void response(Object[] response, Request req, boolean completed) throws Exception{
        //create new Response object and send
    }


    private void response(Response resp, boolean completed) throws Exception {
            response(resp.getResponse(), resp.getRequest(), completed);
    }
}
