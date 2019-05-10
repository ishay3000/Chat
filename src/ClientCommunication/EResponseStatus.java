package ClientCommunication;

import com.google.gson.annotations.SerializedName;

public enum EResponseStatus{
    @SerializedName("OK")
    OK,
    @SerializedName("ERROR")
    ERROR
}
