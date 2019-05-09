package ClientCommunication;

import AuthService.ClientAuthenticator;

import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class RequestHandler {
    private Map<EMessageType, Consumer<BaseMessage>> methodMap = new HashMap<>();
    private ClientAuthenticator clientAuthenticator;
    private ClientWriter clientWriter;

    public RequestHandler(ClientWriter clientWriter) {
        this.clientWriter = clientWriter;
        clientAuthenticator = new ClientAuthenticator(clientWriter);
        initMethods();
    }

    private void initMethods() {
        methodMap.put(EMessageType.LOGIN, clientAuthenticator::authenticateLogin);
        methodMap.put(EMessageType.REGISTER, clientAuthenticator::authenticateRegistration);
    }

    public void handle(BaseMessage message){
        methodMap.get(message.MessageType).accept(message);
    }
}
