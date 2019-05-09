package utils;

import AuthService.AuthMessage;
import ClientCommunication.BaseMessage;
import ClientCommunication.EMessageType;

import java.util.HashMap;
import java.util.Map;

public class MessageConverter {
    private static Map<EMessageType, Class<? extends BaseMessage>> converterMap = new HashMap<>();
    public static MessageConverter OUR_INSTANCE = new MessageConverter();


    private MessageConverter(){
        initMap();
    }

    private void initMap(){
        converterMap.put(EMessageType.LOGIN, AuthMessage.class);
        converterMap.put(EMessageType.REGISTER, AuthMessage.class);
    }

    public static Class<? extends BaseMessage> convert(EMessageType messageType){
        return converterMap.get(messageType);
    }
}
