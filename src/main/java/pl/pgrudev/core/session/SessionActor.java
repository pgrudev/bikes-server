package pl.pgrudev.core.session;

import akka.actor.AbstractActorWithStash;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.event.Logging;
import akka.event.LoggingAdapter;

import javax.inject.Named;

@Named
public abstract class SessionActor extends AbstractActorWithStash {

    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    public static ActorRef getSession(ActorSystem system, String session) {
        return system.actorFor("/user/" + session);
    }

    @Override
    public Receive createReceive() {
        return null;
    }
}
