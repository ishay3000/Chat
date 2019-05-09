import AuthService.AuthMessage;
import ClientCommunication.BaseMessage;
import ClientCommunication.EMessageType;
import Server.Server;
import com.google.gson.Gson;

class Base{
    public int first = 1;
}

class Derived extends Base{
    public int second = 2;
    public String name = "Hello";
}

class MyMessage{
    public EMessageType type = EMessageType.LOGIN;
}

class DerivedMessage extends MyMessage{
    public String username = "Ishay";
    public String password = "1234";
}


public class Main {
    public static void main(String[] args) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
        Server.OUR_INSTANCE.start();

        Gson gson = new Gson();
        BaseMessage message = new AuthMessage(EMessageType.LOGIN, "Ishay", "1234");
        String json = gson.toJson(message);

        BaseMessage recovered = gson.fromJson(json, BaseMessage.class);

        System.out.println(recovered.MessageType);
    }
}