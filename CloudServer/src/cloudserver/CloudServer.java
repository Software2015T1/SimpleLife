/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

/**
 *
 * @author Smith
 */
import java.io.IOException;
import java.net.*;
import java.io.*;
import java.util.List;

public class CloudServer
{

    /**
     * @param args the command line arguments
     */
    public final static int PORT =1028;
    public final String IP_ADDRESS = "127.0.0.1";
    public static UserTable userTable;
   
    public static void main(String[] args)
    {
        ServerSocket server = null;
        try
        {
            // TODO code application logic here
            userTable = UserTableCreator.createUserTable();
            server = new ServerSocket(PORT);
            System.out.println("Server created. waiting for client...");
            new CommandListener().start();
            while(true)
            {
                Socket s = server.accept();
                System.out.println("Connect from: " + s.getInetAddress());
                Client client = new Client(s);
                client.start();
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
        
    }
    
}
class CommandListener extends Thread
{
    public void run()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            try
            {
                String command = reader.readLine();
                if(command.equals("list"))
                {
                   CloudServer.userTable.print();
                }
                else if(command.equals("exit"))
                {
                    System.exit(MIN_PRIORITY);
                }
                
            } catch (IOException ex)
            {
               
            }
        }
    }
}
