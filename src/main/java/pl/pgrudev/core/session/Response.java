package pl.pgrudev.core.session;

public class Response {
    private final Request request;
    private final Throwable failure;
    private final Object response;

    public Response(Request req, Object response, Throwable failure) {
        this.request = req;
        this.failure = failure;
        this.response = response;
    }

   /* public Response(Request request, ){
        this.response = request;
        this.response =
    }
*/
    public Request getRequest() {
        return request;
    }

    public Throwable getFailure() {
        return failure;
    }

   /* public Object[] getResponse(){
        return response.clone();
    }*/
}
