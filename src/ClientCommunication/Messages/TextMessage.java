package ClientCommunication.Messages;

public class TextMessage extends BaseMessage {
    public String Content;
    public String Sender;

    public TextMessage(EMessageType messageType, String content, String sender) {
        super(messageType);
        Content = content;
        Sender = sender;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }
}
