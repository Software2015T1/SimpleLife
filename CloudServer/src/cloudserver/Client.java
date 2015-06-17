package cloudserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Smith
 */
import java.net.*;
import java.io.*;

public class Client extends Thread
{
    private final int NUMofPAR = 5;
    private Socket _s;
    public Client(Socket s)
    {
        this._s = s;
        System.out.println("Client Connect form: " + _s.getInetAddress().toString());
    }
    public void run()
    {
        DataInputStream ds = null;
        DataOutputStream outs = null;
        try
        {
            ds = new DataInputStream(this._s.getInputStream());   
            outs = new DataOutputStream(_s.getOutputStream());
            String command = ds.readUTF();
            String[] par = new String[NUMofPAR];
            MissionType type = CommandParser.parse(command, par);
            switch(type)
            {
                case USER_LOGIN:
                    ds.readUTF();
                    outs.writeUTF("R02");
                break;
                case NEW_ACCOUNT:
                break;
                case MAIN_CONTROLLER_CONNECT:
                break;
                    
            }
        } catch (IOException ex)
        {
            
        } finally
        {
            try
            {
              if(ds!=null)ds.close();
            } catch (IOException ex)
            {
               
            }
        }
    }
}
