/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/**
 *
 * @author Smith
 */
public class ClientTest {
    public static void main(String[] args) {
        
        DataOutputStream outs = null;
        DataInputStream inputs = null;
        BufferedReader br =null;
        try 
        {

            while(true)
            {
                Socket s = new Socket(CloudServer.IP_ADDRESS,CloudServer.PORT);
                outs = new DataOutputStream(s.getOutputStream());
                inputs = new DataInputStream(s.getInputStream());
                br = new BufferedReader(new InputStreamReader(System.in));
                System.out.print("Input command: ");
                String cmd = br.readLine();
                outs.writeUTF(cmd);
                String returnCode = inputs.readUTF();
                System.out.println("Return frome Server: "+returnCode);
               
            }
            
        } catch (IOException ex) 
        {
            System.out.println(ex.toString());
        }
        finally
        {
            try {
                outs.close();
                inputs.close();
            } catch (IOException ex) {
                System.out.println(ex.toString());
            }
            
        }
        
    }
}
