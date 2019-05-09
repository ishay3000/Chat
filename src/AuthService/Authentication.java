package AuthService;

import com.google.gson.Gson;

import java.io.*;

public class Authentication {
    //region members
    BufferedReader mReader;
    BufferedWriter mWriter;

    DataInputStream inputStream;
    DataOutputStream outputStream;
    private final Gson gson = new Gson();


    //endregion


    public Authentication(DataInputStream inputStream, DataOutputStream outputStream) {
        this.inputStream = inputStream;
        this.outputStream = outputStream;
    }

    public Authentication(BufferedReader mReader, BufferedWriter mWriter) {
        this.mReader = mReader;
        this.mWriter = mWriter;
    }

    public boolean authenticateUser(){
        /*try {
            byte[] buffer = new byte[64];
            int read = inputStream.read(buffer);
            String json = new String(buffer).substring(0, read);

            AuthMessage message = gson.fromJson(json, AuthMessage.class);

        } catch (IOException e) {
            e.printStackTrace();
        }*/

        return false;
    }
}
