package ClientCommunication.Messages;

public class BaseMessage {
    public EMessageType MessageType;
    public String Guid;
    public BaseMessage(EMessageType messageType){
        MessageType = messageType;
    }
}
