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
public class Administrator extends Thread
{
    private Socket _s ;
    public Administrator(Socket s)
    {
        this._s = s;
    }
    @Override
    public void run()
    {
        DataInputStream inputs = null;
        DataOutputStream outs = null;
        try
        {
            inputs = new DataInputStream(_s.getInputStream());
            outs = new DataOutputStream(_s.getOutputStream());
            while(true)
            {
                String[] cmd = inputs.readUTF().split(" ");
                switch(cmd[0])
                {
                    case "list":
                        outs.writeUTF(CloudServer.userTable.printToApp());
                        break;
                }
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        } 
        finally
        {
            try
            {
                inputs.close();
                outs.close();
            } catch (IOException ex)
            {
                
            }
        }
        
    }
}
