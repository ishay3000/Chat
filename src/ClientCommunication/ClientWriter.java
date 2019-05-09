package ClientCommunication;

import Server.Server;
import SqlMappings.MySqlUsersEntity;
import com.google.gson.Gson;
import com.mysql.cj.xdevapi.Client;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ClientWriter {
    // region members
    private final Gson gson = new Gson();
    private Socket clientSocket;
    DataOutputStream outputStream;
    private MySqlUsersEntity user;

    // endregion


    public ClientWriter(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public MySqlUsersEntity getUser() {
        return user;
    }

    public void setUser(MySqlUsersEntity user) {
        this.user = user;
    }

    public void writeResponse(ClientResponse response){
        writeJson(gson.toJson(response));
    }


    private void writeJson(String json) {
        byte[] buffer = json.getBytes();

        try {
            outputStream.write(buffer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void unicast(BaseMessage message){
        UnicastMessage unicastMessage = (UnicastMessage)message;
        unicastMessage.setSender(user.getUsername());

        ClientWriter writer = Server.OUR_INSTANCE.getUserClient(unicastMessage.getReceiver());
        writer.writeMessage(unicastMessage);
    }

    public void broadcast(BaseMessage message){
        ConcurrentHashMap map = Server.OUR_INSTANCE.getClientsConnectedMap();
        Collection<ClientWriter> values = map.values();

        values.stream()
                .filter(writer -> writer != this)
                .forEach(writer -> writer.writeMessage(message));
    }

    public void writeMessage(BaseMessage message){
        writeJson(gson.toJson(message));
    }
}
