/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


/**
 *
 * @author Smith
 */
public class RFSender
{
    private Socket _s;
    public RFSender(Socket _s)
    {
        this._s =_s;
    }
    public void WriteUTF(String sendCmd)
    {
        try
        {
            DataOutputStream outs = new DataOutputStream(this._s.getOutputStream());
            outs.writeUTF(sendCmd);
            
        } catch (IOException ex)
        {
            
        }
        
    }
}
