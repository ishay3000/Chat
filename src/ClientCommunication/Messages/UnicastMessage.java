package ClientCommunication.Messages;

public class UnicastMessage extends TextMessage {
    public String Receiver;
    private String sender;

    public UnicastMessage(EMessageType messageType, String content, String receiver) {
        super(messageType, content);
        Receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        Receiver = receiver;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
