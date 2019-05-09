package ClientCommunication;

import AuthService.AuthMessage;
import com.google.gson.Gson;
import utils.MessageConverter;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientReader {
    // region members
    private final Gson gson = new Gson();
    private Socket clientSocket;
    private DataInputStream inputStream;

    //endregion


    public ClientReader(Socket clientSocket) {
        this.clientSocket = clientSocket;
        try {
            inputStream = new DataInputStream(clientSocket.getInputStream());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public BaseMessage read() throws IOException{
            byte[] buffer = new byte[64];
            int read = inputStream.read(buffer);

            String json = new String(buffer).substring(0, read);
            BaseMessage tmp = gson.fromJson(json, BaseMessage.class);

            Class<? extends BaseMessage> typeClass = MessageConverter.convert(tmp.MessageType);
            BaseMessage message = gson.fromJson(json, typeClass);

            return message;
    }
}
