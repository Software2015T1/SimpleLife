package cloudserver;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Smith
 */
import java.net.*;
import java.io.*;

public class Client extends Thread
{
    public static final int NUMofPAR = 6;
    private final Socket _s;
    public Client(Socket s)
    {
        this._s = s;
        System.out.println("Client Connect form: " + _s.getInetAddress().toString());
    }
    @Override
    public void run()
    {
        DataInputStream ds = null;
        DataOutputStream outs = null;
        UserTable usertable = CloudServer.userTable;
        ControllerSocketTable cstable = CloudServer.csTable;
        try
        {
            ds = new DataInputStream(this._s.getInputStream());   
            outs = new DataOutputStream(this._s.getOutputStream());
            String command = ds.readUTF();
            System.out.println(command);
            String[] par = new String[NUMofPAR];
            MissionType type = CommandParser.parse(command, par);
            String returnCode ="";
            switch(type)
            {
                case USER_LOGIN:
                    returnCode = CloudServer.userTable.authenticate(par[0],par[1],_s);
                    outs.writeUTF(returnCode);
                    if("R002".equals(returnCode))
                    System.out.println("Login:"+par[0]+" is logging...");
                    new AppUser(new UserInformation(par[0],"", _s)).start();
                break;
                case NEW_ACCOUNT:
                    UserInformation info = new UserInformation(par[1],"",_s);
                    returnCode = CloudServer.userTable.add(par[0], info);
                    outs.writeUTF(returnCode);
                    if(returnCode.equals("R001"))
                    System.out.println("User Account Created: "+par[0]);
                break;
                case MAIN_CONTROLLER_CONNECT:
                    returnCode = CloudServer.csTable.ControllerConnect(par[0], _s);
                    outs.writeUTF(returnCode);
                    if(returnCode.equals("R008"))
                    System.out.println("Controller connect: "+par[0]+" from: "+_s.getInetAddress());
                    new Controller(_s, par[0]).start();
                break;
                case ADMINISTRATOR_LOGIN:
                    returnCode = CloudServer.userTable.authenticate(par[0],par[1],_s);
                    outs.writeUTF(returnCode);
                    if("R002".equals(returnCode))
                    {
                    System.out.println("Administrator Login:"+par[0]+" is logging...");
                    }
                    new Administrator(_s).start();
                break;
                case None:
                    outs.writeUTF("R999");
                break; 
                default:
                    outs.writeUTF("R998");
                    this._s.close();
                break;

                    
            }
        } catch (IOException ex)
        {
            System.out.println(ex.toString());
        }


    }
}
