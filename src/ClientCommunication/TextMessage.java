package ClientCommunication;

public class TextMessage extends BaseMessage {
    public String Content;

    public TextMessage(EMessageType messageType, String content) {
        super(messageType);
        Content = content;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }
}
