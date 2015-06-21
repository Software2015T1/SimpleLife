/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cloudserver;
import java.net.Socket;
import java.util.List;
/**
 *
 * @author Smith
 */
public class UserInformation
{
    private String _password;
    private String _controllerID;
    private Socket _s;
    public UserInformation(String password,String controllerID,Socket userSocket)
    {
        this._password = password;
        this._controllerID = controllerID;
        this._s = userSocket;
    }
    public String getPassword()
    {
        return this._password;
    }
    public String getControllerID()
    {
        return this._controllerID;
    }
    public Socket getUserSocket()
    {
        return this._s;
    }
    public void setSocket(Socket s)
    {
        this._s = s;
    }
    public void setMController(String mcID)
    {
        this._controllerID = mcID;
    }
    public void setPassword(String password)
    {
        this._password = password;
    }
}
