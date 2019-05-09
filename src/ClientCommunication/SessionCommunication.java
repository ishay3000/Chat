package ClientCommunication;

import java.io.IOException;
import java.net.Socket;

public class SessionCommunication {
    //region members
//    private Socket clientSocket;
    private RequestHandler requestHandler;
    private ClientReader clientReader;
    private ClientWriter clientWriter;


    //endregion


    public SessionCommunication(Socket clientSocket) {
//        this.clientSocket = clientSocket;
        clientReader = new ClientReader(clientSocket);
        clientWriter = new ClientWriter(clientSocket);

        requestHandler = new RequestHandler(clientWriter);
    }

    public void beginSession(){
        BaseMessage message;

        while (true){
            try {
                message = clientReader.read();
                requestHandler.handle(message);
            } catch (IOException e) {
                System.out.println(e.getMessage());
//                endSession();
                break;
            }
        }
    }

    public void endSession(){

    }

}
