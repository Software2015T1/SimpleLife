package cloudserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Smith
 */
public class AppUser
{
    Socket _s ;
    private String[] par = new String[Client.NUMofPAR];
    public AppUser(UserInformation info)
    {
        this._s = info.getUserSocket();
    }
    public void start()
    {
        DataInputStream inputs = null;
        DataOutputStream outs = null;
        try
        {
            inputs = new DataInputStream(_s.getInputStream());
            outs = new DataOutputStream(this._s.getOutputStream());
        } catch (IOException ex)
        {
           System.out.println(ex.toString());
        }
        while(true)
        {
            try
            {
                String command = inputs.readUTF();
                System.out.println(command);
                MissionType type = CommandParser.parse(command, par);
                UserTable usertable = CloudServer.userTable;
                ControllerSocketTable cstable = CloudServer.csTable;
                String returnCode="";
                switch(type)
                {
                    case USER_CHANGEPASSWORD:
                        if(CloudServer.userTable.authenticate(par[0], par[1]))
                        {
                            returnCode = usertable.changePassword(par[0],par[2]);
                            outs.writeUTF(returnCode);

                        }
                        else
                        {
                            outs.writeUTF("R007");
                        }
                        break;
                    case CONTROL_APPLIANCE:
                        if(CloudServer.userTable.authenticate(par[0], par[1]))
                        {
                            Socket s = cstable.getControllerSocket(par[2]);
                            if(s!=null)
                            {
                            returnCode = new Fowarder().send(command, s);
                            outs.writeUTF(returnCode);
                            }
                            else
                            {
                                outs.writeUTF("R015");
                            }
                            System.out.println("User: "+par[0]+" send command to MC: "+par[2]);
                        }
                        else
                        {
                            outs.writeUTF("R007");
                        }
                        break;
                    case ADD_MAINCONTROLLER:
                        if(CloudServer.userTable.authenticate(par[0], par[1]))
                        {
                            Socket s = cstable.getControllerSocket(par[2]);
                            if(s!=null)
                            {
                                try
                                {
                                    CloudServer.userTable.AddMC(par[3],par[2]);
                                    outs.writeUTF("R005");
                                    System.out.println("user: "+par[0]+" add MC: "+par[2]);
                                } catch (Exception e)
                                {
                                    outs.writeUTF("R006");
                                    break;
                                }

                            }
                            else
                            {
                                outs.writeUTF("R006");
                            }
                        }
                        else
                        {
                            outs.writeUTF("R007");
                        }
                         break;
                    case ADD_APPLIANCE:
                        if(CloudServer.userTable.authenticate(par[0], par[1]))
                        {
                            Socket s = cstable.getControllerSocket(par[2]);
                            if(s!=null)
                            {
                                try
                                {
                                   
                                    new Fowarder().send(command,s);
                                    outs.writeUTF("R016");
                                    System.out.println("user: "+par[0]+" add appliance: "+par[4]);
                                } catch (Exception e)
                                {
                                    System.out.println(e);
                                    outs.writeUTF("R017");
                                    break;
                                }

                            }
                            else
                            {
                                outs.writeUTF("R017");
                            }
                        }
                        else
                        {
                            outs.writeUTF("R007");
                        }
                        break;
                    case TIME_SETTING:
                        if(CloudServer.userTable.authenticate(par[0], par[1]))
                        {
                            Socket _s = cstable.getControllerSocket(par[2]);
                            try
                            {
                                new Fowarder().send(command, _s);
                                outs.writeUTF("R016");
                            }
                            catch(Exception ex)
                            {
                                System.out.println(ex);
                            }
                        }
                        else
                        {
                        }
                        break;
                    case IR_CONTROL:
                        Socket s = CloudServer.csTable.getControllerSocket("MC01");
                        //IRMC irmc = Client.irmc;
                        command = par[0]+" "+par[1];
                        new Fowarder().send(command,s);
                        outs.writeUTF("R018");
                        break;
                    case None:
                        outs.writeUTF("R999");
                        break; 
                }
                
            } catch (IOException ex)
            {
                System.out.println(ex.toString());
               break;
            }
            
        }
    }
}
