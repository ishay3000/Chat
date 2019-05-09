package ClientCommunication;

public class UnicastMessage extends TextMessage {
    public String Sender;
    public String Receiver;

    public UnicastMessage(EMessageType messageType, String content, String sender, String receiver) {
        super(messageType, content);
        Sender = sender;
        Receiver = receiver;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
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
