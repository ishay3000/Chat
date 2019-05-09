package ClientCommunication;

public class UnicastMessage extends BaseMessage {
    public String Sender;
    public String Receiver;
    public String Content;

    public UnicastMessage(EMessageType messageType, String sender, String receiver, String content) {
        super(messageType);
        Sender = sender;
        Receiver = receiver;
        Content = content;
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
