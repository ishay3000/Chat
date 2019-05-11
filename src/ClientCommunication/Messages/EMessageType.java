package ClientCommunication.Messages;

import com.google.gson.annotations.SerializedName;

public enum EMessageType {
    @SerializedName("LOGIN")
    LOGIN,

    @SerializedName("REGISTER")
    REGISTER,

    @SerializedName("UNICAST")
    UNICAST,

    @SerializedName("BROADCAST")
    BROADCAST,
}
