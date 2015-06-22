/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;


/**
 *
 * @author Smith
 */
public class RFReceiver extends Thread
{
    private Socket _s;
    public RFReceiver(Socket _s)
    {
        this._s = _s;
        System.out.println("RF Receiver is now waiting for command....");
    }
    public void run()
    {
        DataInputStream inputs = null;
        try
        {
            inputs = new DataInputStream(this._s.getInputStream());
        }
        catch(IOException ex)
        {
            System.out.println(ex);
        }
        while (true)
        {
            try
            {
                String receString = inputs.readUTF();
            } catch (IOException ex)
            {
                
            }
        }

        
    }
}
