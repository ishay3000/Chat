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


    //endregion


    public ClientAuthenticator(ClientWriter clientWriter) {
        this.clientWriter = clientWriter;
    }

    public void authenticateRegistration(BaseMessage message){
        AuthMessage registerMessage = (AuthMessage)message;

        MySqlUsersEntity user = new MySqlUsersEntity(registerMessage.Username, registerMessage.Password);
        ClientResponse response = new ClientResponse(EResponseStatus.ERROR);
        // register to db via dao
        if (UserDao.getOurInstance().add(user)){
            response.Status = EResponseStatus.OK;
        } else {
            response.Cause = "User with such name already exists";
        }
        clientWriter.writeResponse(response);
    }

    public void authenticateLogin(BaseMessage message){
        AuthMessage loginMessage = (AuthMessage)message;
        MySqlUsersEntity user = new MySqlUsersEntity(loginMessage.Username, loginMessage.Password);

        ClientResponse response = new ClientResponse(EResponseStatus.ERROR);
        // login via dao
        if (UserDao.getOurInstance().exists(user)){
            // server.addUserToClientsConnected(user)
            Server.OUR_INSTANCE.addClient(user, clientWriter);
            response.Status = EResponseStatus.OK;
            clientWriter.setUser(user);
        } else {
            response.Cause = "User with such name does not exist.";
        }
        clientWriter.writeResponse(response);
    }
}
