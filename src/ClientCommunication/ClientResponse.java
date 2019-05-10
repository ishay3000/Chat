package ClientCommunication;

public class ClientResponse {
    public EResponseStatus Status;
    public String Cause;

    public ClientResponse(EResponseStatus responseStatus, String cause) {
        this.Status = responseStatus;
        Cause = cause;
    }

    public ClientResponse(EResponseStatus responseStatus) {
        this.Status = responseStatus;
    }
}
