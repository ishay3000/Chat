package AuthService;

import ClientCommunication.Messages.AuthMessage;
import ClientCommunication.Messages.BaseMessage;
import ClientCommunication.ClientResponse;
import ClientCommunication.ClientWriter;
import ClientCommunication.Messages.EResponseStatus;
import Dao.UserDao;
import Server.Server;
import SqlMappings.MySqlUsersEntity;
import com.google.gson.Gson;

public class ClientAuthenticator {
    private ClientWriter clientWriter;
    private final Gson gson = new Gson();


    //endregion


    public ClientAuthenticator(ClientWriter clientWriter) {
        this.clientWriter = clientWriter;
    }

    public void authenticateRegistration(BaseMessage message){
        AuthMessage registerMessage = (AuthMessage)message;
        MySqlUsersEntity user = new MySqlUsersEntity(registerMessage.Username, registerMessage.Password);

        EResponseStatus responseStatus = EResponseStatus.ERROR;
        // register to db via dao
        if (UserDao.getOurInstance().add(user)){
            responseStatus = EResponseStatus.OK;
        }
        clientWriter.writeResponse(new ClientResponse(responseStatus));
    }

    public void authenticateLogin(BaseMessage message){
        AuthMessage loginMessage = (AuthMessage)message;
        MySqlUsersEntity user = new MySqlUsersEntity(loginMessage.Username, loginMessage.Password);

        EResponseStatus responseStatus = EResponseStatus.ERROR;
        // login via dao
        if (UserDao.getOurInstance().exists(user)){
            // server.addUserToClientsConnected(user)
            Server.OUR_INSTANCE.addClient(user, clientWriter);
            responseStatus = EResponseStatus.OK;
        }
        clientWriter.setUser(user);
        clientWriter.writeResponse(new ClientResponse(responseStatus));
    }
}
