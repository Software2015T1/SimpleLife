package com.example.user.simplelife;

import android.util.Log;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

/**
 * Created by bostenkg5 on 2015/6/23.
 */
public class CommandCreator {
    String command;
    public String createCommand(ArrayList<String> strings) {

        command = "";
        for(int i=0 ; i < strings.size() ;i++){
            command = command+strings.get(i);
            if(i!=strings.size()-1){
                command= command+" ";
            }
        }
      return command;
    }

    public void sendToServer(){
        new Thread() {
            public void run() {
                Socket s = UserProfile.Socket2Server;
                try
                {
                    DataOutputStream outs = new DataOutputStream(s.getOutputStream());
                    outs.writeUTF(command);
                } catch (
                        IOException e
                        )

                {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}

