package com.example.user.simplelife;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by Smith on 2015/6/21.
 */
public class UserProfile {
    public static Socket Socket2Server;
    public static String email;
    public static String username;
    public static String password;
    public static void Dispose()
    {
        try {
            Socket2Server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Socket2Server =null;
        email = null;
        username = null;
        password = null;
    }

}
