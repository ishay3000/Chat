package ClientCommunication;

public class BaseMessage {
    public EMessageType MessageType;
    public BaseMessage(EMessageType messageType){
        MessageType = messageType;
    }
}
