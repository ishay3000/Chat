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


    // endregion


    public ClientWriter(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
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
