package pl.pgrudev.core.netty;

import akka.actor.ActorRef;
import akka.dispatch.Futures;
import akka.dispatch.OnComplete;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.index.Indexed;
import pl.pgrudev.client.SessionApiImpl;
import pl.pgrudev.core.api.ClientApi;
import pl.pgrudev.core.api.ClientApiImpl;
import pl.pgrudev.core.api.repo.MethodsRepo;
import pl.pgrudev.core.session.Request;
import pl.pgrudev.core.session.Response;
import pl.pgrudev.core.session.SessionActor;
import pl.pgrudev.nextbike.NextBikeApiImpl;
import pl.pgrudev.nextbike.model.Dictionary;
import pl.pgrudev.repository.UserRepository;
import scala.concurrent.Future;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

@Named("WebsocketSessionActor")
@Scope("prototype")
public class WebSocketSessionActor extends SessionActor {
    private final LoggingAdapter logger = Logging.getLogger(getContext().system(), this);

    private ChannelHandlerContext ctx;
    private Gson gson;
    private boolean adminSession;

    @Inject
    private MethodsRepo methodsRepo;
    private List<String> loginNotRequired;
    private Map<String, Method> methodsMap;
    private List<String> adminCommands;

    private ClientApi clientApiImpl;
    @Inject
    private NextBikeApiImpl nextBikeApi;
    @Inject
    private SessionApiImpl sessionApi;
    @Inject
    private Dictionary dictionary;
    @Inject
    private UserRepository userRepository;


    public WebSocketSessionActor(ChannelHandlerContext ctx) {
        super(ctx);
        this.ctx = ctx;
    }

    @Override
    public void preStart() throws Exception {
        logger.debug("Actor starting");
        this.gson = new Gson();
        this.methodsMap = methodsRepo.getMethodsMap(ClientApi.class);
        this.loginNotRequired = methodsRepo.getLoginNotRequiredCommands(ClientApi.class).stream().map(Method::getName).collect(Collectors.toList());
        this.adminCommands = methodsRepo.getAdminOnlyCommands(ClientApi.class).stream().map(Method::getName).collect(Collectors.toList());
        this.clientApiImpl = new ClientApiImpl(this, nextBikeApi, dictionary, userRepository);
        super.preStart();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(String.class, msg -> {
                    logger.info("Received message: " + msg);
                    try {
                        Request request = gson.fromJson(msg, Request.class);
                        parsePrimitives(request);
                        response(handleRequest(request), request);
                    } catch (JsonSyntaxException e) {
                        logger.warning("Error in parsing message: " + msg);
                    }
                })
                .match(Response.class, this::send)
                .build();
    }

    private void parsePrimitives(Request request) {
        if (null != request.getArgs()) {
            Object[] args = request.getArgs();
            for (int i = 0; i < args.length; i++) {
                Object arg = args[i];
                if(arg instanceof Double) {
                    Integer value = ((Double) arg).intValue();
                    if (value == ((Double) arg).doubleValue()) {
                      args[i] = value;
                    }
                }
            }
        }
    }

    @Override
    public Future<Object> handleRequest(final Request request) {
        if (request == null) {
            return Futures.failed(new IllegalArgumentException("Request empty"));
        }

        if (!isLoggedIn() && isLoginRequired(request)) {
            return Futures.failed(new IllegalAccessError("User not logged in"));
        }

        if (isUserEligible(request)) {
            return callApi(request);
        } else {
            return Futures.failed(new IllegalAccessError("User not eligible"));
        }

    }

    private boolean isUserEligible(Request request) {
        if (adminCommands.contains(request.getCommand().getCmd())) {
            return !adminSession;
        } else return true;
    }


    private boolean isLoginRequired(Request request) {
        return !loginNotRequired.contains(request.getCommand().getCmd());
    }

    private Future<Object> callApi(Request request) {
        Method method = methodsMap.get(request.getCommand().getCmd());
        Callable<Object> call = () -> {
            try {
                if (null != request.getArgs()) {
                    return method.invoke(clientApiImpl, request.getArgs());
                }
                return method.invoke(clientApiImpl);
            } catch (Exception e) {
                logger.error("Invocation Target Exception - calling api", e);
                throw e;
            }
        };
        return Futures.future(call, getContext().dispatcher());
    }

    private void response(Future<Object> response, final Request req) {

        final ActorRef self = getSelf();
        final ActorRef sender = getSender();

        response.onComplete(new OnComplete<Object>() {

            @Override
            public void onComplete(Throwable failure, Object response) throws Throwable {
                self.tell(new Response(req, response, failure), sender);
            }

        }, context().dispatcher());
    }


    @Override
    public void response(Request req, Object response, boolean completed) {
        send(createResponse(req));
    }

    @Override
    public void send(Response response) {
        super.send(response);
    }

    private Response createResponse(Request request) {
        String[] response = new String[]{"Request was sent by", this.getSelf().toString()};
        return new Response(request, response, null);
    }

    @Override
    public void postStop() {
        ctx.close();
        super.postStop();
    }
}
