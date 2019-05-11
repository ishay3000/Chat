package ClientCommunication.Messages;

public class BroadcastMessage extends TextMessage {
    public BroadcastMessage(String content, String sender) {
        super(EMessageType.BROADCAST, content, sender);
    }
}
