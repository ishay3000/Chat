package Server;

import ClientCommunication.ClientWriter;
import ClientCommunication.SessionCommunication;
import SqlMappings.MySqlUsersEntity;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {
    //#region members

    public static Server OUR_INSTANCE = new Server();
    private static final int PORT = 8090;
    private ServerSocket mServerSocket;
    private ConcurrentHashMap<MySqlUsersEntity, ClientWriter> clientsConnectedMap;


    //#endregion

    private Server() {
        try {
            mServerSocket = new ServerSocket(PORT);
            clientsConnectedMap = new ConcurrentHashMap<>();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public ConcurrentHashMap<MySqlUsersEntity, ClientWriter> getClientsConnectedMap() {
        return clientsConnectedMap;
    }

    /**
     * adds the user-socket pair to the connected clients map
     * @param user the user to add
     * @param clientWriter the user's socket writer
     */
    public void addClient(MySqlUsersEntity user, ClientWriter clientWriter){
        clientsConnectedMap.put(user, clientWriter);
    }

    /**
     * start listening for client connections
     */
    public void start() {
        System.out.println(String.format("Listening on port [%d]...", PORT));

        // indefinitely listen for clients
        while (true){

            try {
                Socket clientSocket = mServerSocket.accept();
                System.out.println("[+] Client connected on: " + clientSocket.getInetAddress());
                SessionCommunication sessionCommunication = new SessionCommunication(clientSocket);

                new Thread(() -> sessionCommunication.beginSession()).start();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }


}
