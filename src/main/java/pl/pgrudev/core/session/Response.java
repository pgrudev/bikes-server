package pl.pgrudev.core.session;

import org.bson.types.ObjectId;

public class Response {
    private final Request request;
    private final Throwable failure;
    private final Object response;

    public Response(Request req, Object response, Throwable failure) {
        this.request = req;
        this.failure = failure;
        this.response = response;
    }

    public Response(Object response){
        this.request=null;
        this.response = response;
        this.failure = null;
    }

    public Request getRequest() {
        return request;
    }

    public Throwable getFailure() {
        return failure;
    }

    public Object getResponse(){
        return this.response;
    }

    @Override
    public String toString() {
        return "Response{" +
                "request=" + request +
                ", failure=" + failure +
                ", response=" + response +
                '}';
    }
}
