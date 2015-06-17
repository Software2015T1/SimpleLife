/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Smith
 */
public class MCTest
{
    public static void main(String[] args)
    {
        try
        {
            Socket s = new Socket(CloudServer.IP_ADDRESS,CloudServer.PORT);
            DataInputStream ins = new DataInputStream(s.getInputStream());
            DataOutputStream outs = new DataOutputStream(s.getOutputStream());
            String connectCMD = "/MCConnect MC01";
            outs.writeUTF(connectCMD);
            String returnCode = ins.readUTF();
            System.out.println(returnCode);
            while(true)
            {
                try
                {
                    String cmd = ins.readUTF();
                    System.out.println(cmd);
                } 
                catch (EOFException e)
                {
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex)
                    {
                        Logger.getLogger(MCTest.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                
            }
            
            
            
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }
}
