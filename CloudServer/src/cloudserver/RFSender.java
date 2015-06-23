/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
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
            outs.flush();

            
        } catch (IOException ex)
        {
            System.out.println(ex);
        }
        
    }
    public void WriteString(String dst)
    {
        try
        {
//            PrintWriter writer = new PrintWriter(this._s.getOutputStream());
//            writer.println(dst);
//            writer.flush();
            char[] arr = dst.toCharArray();
            OutputStream outs = this._s.getOutputStream();
           
//            for(int i=0;i<arr.length;i++)
//            {
//                outs.write(arr[i]);
//            }
            outs.write(dst.getBytes("UTF-8"));
            outs.close();
        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
    public void Write(int i)
    {
        try
        {
            DataOutputStream outs = new DataOutputStream(this._s.getOutputStream());
            outs.writeChar(i);
            
        } catch (IOException ex)
        {
            System.out.println(ex);
        }
    }
}
