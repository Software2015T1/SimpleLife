/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Smith
 */
public class CSocket
{
    private static ServerSocket server;
    private static final int PORT =8888;
    
    public static void main(String[] args) 
    {
        try
        {
            server = new ServerSocket(PORT);
            System.out.println("Server is created, waiting for client...");
            while(true)
            {
                Socket client = server.accept();
                System.out.println("Client form: "+client.getInetAddress());
                DataInputStream inputs = new DataInputStream(client.getInputStream());
                byte[] buffer = new byte[200];
                inputs.read(buffer);
                System.out.println(new String(buffer,"utf-8"));
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("input data: ");
                String sendMessage = reader.readLine();
                DataOutputStream outs= new DataOutputStream(client.getOutputStream());
                outs.write(sendMessage.getBytes("utf-8"),0,sendMessage.length());
                
            }
            
        } catch (IOException ex)
        {
           System.out.println(ex);
        }
        
    }
}
