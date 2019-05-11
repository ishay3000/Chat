package ClientCommunication.Messages;

public class UnicastMessage extends TextMessage {
    public String Receiver;

    public UnicastMessage(String content, String sender, String receiver) {
        super(EMessageType.UNICAST, content, sender);
        this.Receiver = receiver;
    }

    public String getReceiver() {
        return Receiver;
    }

    public void setReceiver(String receiver) {
        this.Receiver = receiver;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
