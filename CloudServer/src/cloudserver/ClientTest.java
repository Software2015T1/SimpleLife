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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


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

                System.out.print("account: ");
                String account = br.readLine();
                System.out.print("password: ");
                String password = Md5.md5(br.readLine());
                System.out.print("Input commandPrefix: ");
                String cmd = br.readLine();
                System.out.print("Input commandPar: ");
                String par = br.readLine();
                cmd = cmd+" "+account+" "+password+par;
                System.out.println(cmd);
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
