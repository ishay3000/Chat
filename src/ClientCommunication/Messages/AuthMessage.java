package ClientCommunication.Messages;

public class AuthMessage extends BaseMessage {
    public String Username;
    public String Password;

    public AuthMessage(EMessageType messageType, String username, String password) {
        super(messageType);
        this.Username = username;
        this.Password = password;
    }
}
