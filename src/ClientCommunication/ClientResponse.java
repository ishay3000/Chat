package ClientCommunication;

public class ClientResponse {
    public EResponseStatus responseStatus;
    public String Cause;

    public ClientResponse(EResponseStatus responseStatus, String cause) {
        this.responseStatus = responseStatus;
        Cause = cause;
    }

    public ClientResponse(EResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
