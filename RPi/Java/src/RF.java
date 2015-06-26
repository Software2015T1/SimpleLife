
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class RF{
    //private MCReadFile mCReadFile;
    //private MCWriteFile mCWriteFile;

    private DeviceInfo deviceInfo;
    public void initial(DeviceInfo deviceInfo){
        this.deviceInfo = deviceInfo;
        
    } 
	public void controll(String ID,String[] cmd,int type){
		System.out.printf("%s %s %s",ID,cmd[0],cmd[1]);
	}
    public void controll(String ID,String[] cmd){
		System.out.printf("int RF : deviceId=%s : %s %s\n",ID,cmd[0],cmd[1]);
       /*
		String ardID = deviceInfo.getArdIDFromMap(ID);
        String type = deviceInfo.getTypeFromMap(ID);
        String flag;//p or i
        String act;
        if(type.equals("Light")){
            flag="p";
            if(cmd[1].equals("off"))
                act="0";
            else if(cmd[1].equals("on"));
                act="1";
        }

        String total = ardID+flag+act;
        mCWriteFile.write(total);

*/
	return;
    }
    
}