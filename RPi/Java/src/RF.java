/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 *
 * @author Smith
 */
public class RF extends Thread
{
    private ServerSocket server;
    private final String localIP ="127.0.0.1";
    private final int PORT = 5123;
    private Socket _s;
    private RFReceiver rfR;
    private RFSender rfS;
    public RF()
    {
        
    }
    public void run()
    {
        try
        {
            server = new ServerSocket(PORT);
            System.out.println("RF Server is creating...");
            _s = server.accept();
            System.out.println(_s.getInetAddress()+" is connected");
            rfR = new RFReceiver(_s);
            rfR.start();
            rfS = new RFSender(_s);
             System.out.println("RF Server Mission Completed!");
        } catch (IOException ex)
        {
           System.out.println(ex.toString());
        }
    }
    public RFReceiver getRFReceiver()
    {
        return rfR;
    }
    public RFSender getRFSender()
    {
        return rfS;
    }
    public static void main(String[] args)
    {
        
    }
}
