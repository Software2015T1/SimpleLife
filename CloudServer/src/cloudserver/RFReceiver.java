/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
       // while (true)
       // {
            try
            {
                BufferedReader br = new BufferedReader(new InputStreamReader(this._s.getInputStream()));
                String recString = br.readLine();
                System.out.println(recString);
            } catch (IOException ex)
            {
                System.out.println(ex);
            }
            
       // }

        
    }
}
