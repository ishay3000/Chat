package ClientCommunication;

import ClientCommunication.Messages.BaseMessage;

import java.io.IOException;
import java.net.Socket;

public class SessionCommunication {
    //region members
//    private Socket clientSocket;
    private RequestHandler requestHandler;
    private ClientReader clientReader;
    private ClientWriter clientWriter;
    private boolean isRunning = true;

    //endregion


    public SessionCommunication(Socket clientSocket) {
        clientReader = new ClientReader(clientSocket);
        clientWriter = new ClientWriter(clientSocket);

        requestHandler = new RequestHandler(clientWriter);
    }

    public void beginSession(){
        BaseMessage message;

        while (isRunning){
            try {
                message = clientReader.read();
                requestHandler.handle(message);
            } catch (IOException e) {
                endSession();
                System.out.println("[-] Client disconnected.");
            }
        }
    }

    public void endSession(){
        Server.Server.OUR_INSTANCE.removeClient(clientWriter);
        isRunning = false;
    }

}
