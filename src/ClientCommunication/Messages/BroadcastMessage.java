package ClientCommunication.Messages;

public class BroadcastMessage extends TextMessage {
    public BroadcastMessage(EMessageType messageType, String content) {
        super(messageType, content);
    }
}