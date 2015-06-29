/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;

/**
 *
 * @author Smith
 */
enum MissionType
{
    USER_LOGIN,
    NEW_ACCOUNT,
    SWITCH_APPLIANCE,
    MAIN_CONTROLLER_CONNECT,
    ADD_MAINCONTROLLER,
    CONTROL_APPLIANCE,
    ADMINISTRATOR_LOGIN,
    USER_CHANGEPASSWORD,
    ADD_APPLIANCE,
    TIME_SETTING,
    None,
}
public class CommandParser
{
    static MissionType parse(String command,String[] par)
    {
        MissionType type = MissionType.None;

            String[] cmd = command.split(" ");
            String prefix = cmd[0];
            try
            {    
                switch (prefix) 
                {
                    case "/Register":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        type = MissionType.NEW_ACCOUNT;
                        break;
                    case "/Login":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        type = MissionType.USER_LOGIN;
                        break;
                    case "/AddMC":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        par[2] = cmd[3];
                        par[3] = cmd[4];
                        type = MissionType.ADD_MAINCONTROLLER;
                    break;
                    case "/MCConnect":
                        par[0] = cmd[1];
                        type = MissionType.MAIN_CONTROLLER_CONNECT;
                    break;
                    case "/ControlAppliance":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        par[2] = cmd[3];
                        type = MissionType.CONTROL_APPLIANCE;
                    break;
                    case "/ALogin":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        type =MissionType.ADMINISTRATOR_LOGIN;
                        break;
                    case "/ChangePassword":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        par[2] = cmd[3];
                        type =MissionType.USER_CHANGEPASSWORD;
                        break;
                    case "/AddAppliance":
                         par[0] = cmd[1];
                         par[1] = cmd[2];
                         par[2] = cmd[3];
                         par[3] = cmd[4];
                         par[4] = cmd[5];
                         if(cmd.length>6)par[5]=cmd[6];
                         type=MissionType.ADD_APPLIANCE;
                        break;
                    case "/TimeSetting":
                        par[0] = cmd[1];
                        par[1] = cmd[2];
                        par[2] = cmd[3];
                        type=MissionType.TIME_SETTING;
                        break;
                }
            }
            catch(Exception e)
            {
                return MissionType.None;
            }
   
        
        
        
        return type;
    }
}
