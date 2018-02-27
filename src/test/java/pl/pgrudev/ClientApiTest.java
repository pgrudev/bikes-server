package pl.pgrudev;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.testkit.javadsl.TestKit;
import io.netty.channel.ChannelHandlerContext;
import org.junit.Assert;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import pl.pgrudev.core.netty.WebSocketSessionActor;

import static pl.pgrudev.core.spring.SpringExtension.SPRING_EXTENSION_PROVIDER;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ClientApiTest  {

    static ActorSystem actorSystem;

    @Mock
    ChannelHandlerContext ctx;

    @BeforeAll
    static void setup() {
       // actorSystem = ActorSystem.create("test");
    }

    @AfterAll
    static void teardown() {
       // TestKit.shutdownActorSystem(actorSystem);
        actorSystem = null;
    }

    @Test
    public void simpleTest() throws Exception {
        Assert.assertEquals(true, true);
    }

   /* @Test
    void actorPingTest() throws Exception {
        new TestKit(actorSystem) {{
            final Props props = Props.create(WebSocketSessionActor.class);

            final ActorRef wsActor = actorSystem.actorOf(SPRING_EXTENSION_PROVIDER.get(actorSystem).props("WebsocketSessionActor", ctx));
            final ActorRef subject = actorSystem.actorOf(props);
            final TestKit probe = new TestKit(actorSystem);
            subject.tell(probe.getRef(), getRef());
            expectMsg(duration("1 second"), "done");
            String msg = "{\n" +
                    "\t\"command\": {\n" +
                    "\t\t\"cmd\": \"ping\"\n" +
                    "\t}\n" +
                    "}";
            wsActor.tell(msg, getRef());
            within(duration("1 second"), () -> {
               *//* subject.tell("hello", getRef());

                // This is a demo: would normally use expectMsgEquals().
                // Wait time is bounded by 3-second deadline above.
                expectMsgEquals("hello");

                // response must have been enqueued to us before probe
                expectMsg(Duration.Zero(), "world");
                // check that the probe we injected earlier got the msg
                probe.expectMsg(Duration.Zero(), "hello");
                assertEquals(getRef(), probe.getLastSender());*//*
*//*

                Request req = new Request(new Command("ping"), null);
                String pong = "pong";
                Response expected = new Response(req, pong, null);
*//*

                wsActor.tell(msg, getRef());

                // Will wait for the rest of the 3 seconds
                expectNoMsg();
                return null;
            });

        }};
    }*/
}
