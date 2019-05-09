package AuthService;

import ClientCommunication.BaseMessage;
import ClientCommunication.ClientResponse;
import ClientCommunication.ClientWriter;
import ClientCommunication.EResponseStatus;
import Dao.UserDao;
import Server.Server;
import SqlMappings.MySqlUsersEntity;
import com.google.gson.Gson;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ClientAuthenticator {
    private ClientWriter clientWriter;
    private final Gson gson = new Gson();


    //endregion


    public ClientAuthenticator(ClientWriter clientWriter) {
        this.clientWriter = clientWriter;
    }

    public void authenticateRegistration(BaseMessage message){
        AuthMessage registerMessage = (AuthMessage)message;
        MySqlUsersEntity user = new MySqlUsersEntity(registerMessage.username, registerMessage.password);

        EResponseStatus responseStatus = EResponseStatus.ERROR;
        // register to db via dao
        if (UserDao.OUR_INSTANCE.add(user)){
            responseStatus = EResponseStatus.OK;
        }
        clientWriter.writeResponse(new ClientResponse(responseStatus));
    }

    public void authenticateLogin(BaseMessage message){
        AuthMessage loginMessage = (AuthMessage)message;
        MySqlUsersEntity user = new MySqlUsersEntity(loginMessage.username, loginMessage.password);

        EResponseStatus responseStatus = EResponseStatus.ERROR;
        // login via dao
        if (UserDao.OUR_INSTANCE.exists(user)){
            // server.addUserToClientsConnected(user)
            Server.OUR_INSTANCE.addClient(user, clientWriter);
            responseStatus = EResponseStatus.OK;
        }
        clientWriter.setUser(user);
        clientWriter.writeResponse(new ClientResponse(responseStatus));
    }
}
