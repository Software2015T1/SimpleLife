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

public class CloudServer
{

    /**
     * @param args the command line arguments
     */
    public final static int PORT =1028;
    public final static String IP_ADDRESS = "127.0.0.1";
    public static UserTable userTable;
    public static ControllerSocketTable csTable;
    public static void main(String[] args)
    {
        ServerSocket server = null;
        try
        {
            // TODO code application logic here
            userTable = UserTableCreator.createUserTable();
            csTable = ControllerSocketTableCreator.createCSTable();
            server = new ServerSocket(PORT);
            System.out.println("Server created. waiting for client...");
            new CommandListener().start();
            while(true)
            {
                Socket s = server.accept();
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
    @Override
    public void run()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while(true)
        {
            try
            {
                String[] command = reader.readLine().split(" ");
                if(command[0].equals("list"))
                {
                   CloudServer.userTable.print();
                   CloudServer.csTable.print();
                }
                else if(command[0].equals("exit"))
                {
                   System.exit(MIN_PRIORITY);
                }
                else if(command[0].equals("save"))
                {
                    CloudServer.userTable.SaveXml();
                    CloudServer.csTable.SaveXml();
                    System.out.println("Table saving...");
                }
                else if(command[0].equals("AddMC"))
                {
                    CloudServer.csTable.addAuthenticationController(command[1]);
                    System.out.println("MC added: " + command[1]);
                }
               
                
            } catch (IOException ex)
            {
               
            }
        }
    }
}
