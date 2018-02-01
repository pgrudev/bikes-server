package pl.pgrudev;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import pl.pgrudev.client.Command;
import pl.pgrudev.core.netty.WebSocketSessionActor;
import pl.pgrudev.core.session.Request;
import pl.pgrudev.core.session.Response;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientApiTest {

    static ActorSystem actorSystem;

    @BeforeClass
    static void setup() {
        actorSystem = ActorSystem.create();
    }

    @AfterClass
    static void teardown() {
        TestKit.shutdownActorSystem(actorSystem);
        actorSystem = null;
    }

    @Test
    void loginTest() throws Exception {
        new TestKit(actorSystem) {{
            final Props props = Props.create(WebSocketSessionActor.class);
            final ActorRef subject = actorSystem.actorOf(props);
            final TestKit probe = new TestKit(actorSystem);
            subject.tell(probe.getRef(), getRef());
            expectMsg(duration("1 second"), "done");
            within(duration("1 second"), () -> {
               /* subject.tell("hello", getRef());

                // This is a demo: would normally use expectMsgEquals().
                // Wait time is bounded by 3-second deadline above.
                expectMsgEquals("hello");

                // response must have been enqueued to us before probe
                expectMsg(Duration.Zero(), "world");
                // check that the probe we injected earlier got the msg
                probe.expectMsg(Duration.Zero(), "hello");
                assertEquals(getRef(), probe.getLastSender());*/

                Request req = new Request(new Command("ping"), null);
                String pong = "pong";
                Response expected = new Response(req, pong, null);

                // Will wait for the rest of the 3 seconds
                expectNoMsg();
                return null;
            });

        }};
    }
}
