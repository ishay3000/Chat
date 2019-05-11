package ClientCommunication;

import ClientCommunication.Messages.BaseMessage;
import ClientCommunication.Messages.BroadcastMessage;
import ClientCommunication.Messages.EResponseStatus;
import ClientCommunication.Messages.UnicastMessage;
import Server.Server;
import SqlMappings.MySqlUsersEntity;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Collection;
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
        unicastMessage.setSender(Server.OUR_INSTANCE.getUserName(this));
        try {
            ClientWriter writer = Server.OUR_INSTANCE.getUserClient(unicastMessage.getReceiver());
            writer.writeMessage(unicastMessage);
        } catch (NullPointerException e){
            this.writeResponse(new ClientResponse(EResponseStatus.ERROR, "Couldn't send message - user not found.", message.Guid));
        }
    }

    public void broadcast(BaseMessage message){
        BroadcastMessage broadcastMessage = (BroadcastMessage)message;

        broadcastMessage.setSender(Server.OUR_INSTANCE.getUserName(this));

        ConcurrentHashMap map = Server.OUR_INSTANCE.getClientsConnectedMap();
        Collection<ClientWriter> values = map.values();

        values.stream()
                .filter(writer -> writer != this)
                .forEach(writer -> writer.writeMessage(broadcastMessage));
    }

    public void writeMessage(BaseMessage message){
        writeJson(gson.toJson(message));
    }
}
