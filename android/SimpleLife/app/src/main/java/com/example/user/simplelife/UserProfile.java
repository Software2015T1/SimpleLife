package com.example.user.simplelife;

import android.app.Activity;
import android.app.ProgressDialog;

import com.google.android.gms.common.api.Scope;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
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
    public static String homeAddress = null;
    private static final long WaitTime = 100000;
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
    public static String ReConnect2Server(ProgressDialog dialog,final String email, final String password)
    {

        String returnCode="R997";
        Stopwatch watch = new Stopwatch();
                while (true && watch.getElapsedTime().getElapsedRealtimeMillis()<WaitTime) {
                    try {
                        Socket2Server = new Socket("140.112.53.245", 1028);
                        DataOutputStream outs = new DataOutputStream(Socket2Server.getOutputStream());
                        DataInputStream inputs = new DataInputStream(Socket2Server.getInputStream());
                        outs.writeUTF("/Login " + email + " " + password);
                        returnCode = inputs.readUTF();
                        break;
                    } catch (IOException e) {
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                    }
                }

        dialog.dismiss();
        return returnCode;
    }

}
