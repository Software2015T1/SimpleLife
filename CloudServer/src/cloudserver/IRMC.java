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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Smith
 */
public  class IRMC 
{
    private  Socket _s;
     DataInputStream inputs;
     DataOutputStream outs;
    public IRMC(Socket _s)
    {
        this._s = _s;
        try
        {
            inputs = new DataInputStream(this._s.getInputStream());
            outs = new DataOutputStream(this._s.getOutputStream());
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
       
    }
    public void writeUTF(String cmd)
    {
        try
        {
            outs.writeUTF(cmd);
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }
    }
}
