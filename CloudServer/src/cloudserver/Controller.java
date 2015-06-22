/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Smith
 */
public class Controller
{
    private Socket _s;
    private String _ID;
    private String[] par = new String[Client.NUMofPAR];
    public Controller(Socket _s,String _ID)
    {
        this._s = _s;
        this._ID = _ID;
    }
    public void start()
    {
        DataInputStream inputs = null;
        DataOutputStream outs = null;
        try
        {
            inputs = new DataInputStream(this._s.getInputStream());
            outs = new DataOutputStream(this._s.getOutputStream());
            
        } catch (IOException ex)
        {
        }
 
        try
            {
                while(true)
                {
                    String command = inputs.readUTF();
                    System.out.println("MC says: "+command);
                    MissionType type = CommandParser.parse(command, par);
                    UserTable usertable = CloudServer.userTable;
                    ControllerSocketTable cstable = CloudServer.csTable;
                    String returnCode="";
                    switch(type)
                    {

                    }
                }
            } catch (IOException ex1)
            {
                
            }

        
        
    }
}
