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
}
public class CommandParser
{
    static MissionType parse(String command,String[] par)
    {
        MissionType type = MissionType.USER_LOGIN;
        return type;
    }
}
