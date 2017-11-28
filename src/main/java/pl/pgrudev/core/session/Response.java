package pl.pgrudev.core.session;

public class Response {
    private final Request request;
    private final String failure;
    private final Object response;

    public Response(Request req, Object response, Throwable failure) {
        this.request = req;
        if (failure != null) {
            this.failure = failure.getMessage();
        } else {
            this.failure = null;
        }
        this.response = response;
    }

    public Response(Object response) {
        this.request = null;
        this.response = response;
        this.failure = null;
    }

    public Request getRequest() {
        return request;
    }

    public String getFailure() {
        return failure;
    }

    public Object getResponse() {
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
