package AuthService;

import ClientCommunication.BaseMessage;
import ClientCommunication.EMessageType;

public class AuthMessage extends BaseMessage {
    public String username;
    public String password;

    public AuthMessage(EMessageType messageType, String username, String password) {
        super(messageType);
        this.username = username;
        this.password = password;
    }
}
