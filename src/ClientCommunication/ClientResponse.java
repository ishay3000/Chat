package ClientCommunication;

import ClientCommunication.Messages.EResponseStatus;

public class ClientResponse {
    public EResponseStatus Status;
    public String Cause;
    public String Guid;

    public ClientResponse(EResponseStatus status, String cause, String guid) {
        Status = status;
        Cause = cause;
        Guid = guid;
    }

    public ClientResponse(EResponseStatus responseStatus, String cause) {
        this.Status = responseStatus;
        Cause = cause;
    }

    public ClientResponse(EResponseStatus responseStatus) {
        this.Status = responseStatus;
    }
}
