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
public class Fowarder
{
    public String send(String cmd , Socket dst)
    {
        try
        {
            DataOutputStream outs = new DataOutputStream(dst.getOutputStream());
            outs.writeUTF(cmd);
            return "R010";
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
            return "R011";
        }
        
    }
}
