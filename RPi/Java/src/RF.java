import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class RF extends Thread{
    private DeviceInfo deviceInfo;
	private Socket client;
	private volatile boolean running = true;
	private ArrayList<RFclient>clientlist;
	private Socket tmp_client;
	ServerSocket server;
	RFclient c;
	public RF(){
		System.out.println("build RF connection and waiting");
		clientlist = new ArrayList<RFclient>();
		go();
	}
	private void go(){
		try{
			server = new ServerSocket(5566); 
		}catch (IOException e){
			e.printStackTrace();
		}
	}
	@Override
	public void run(){
		while(running){
         System.out.println("RF is our God.");
			try{
				tmp_client = server.accept();
				System.out.println("building connection");
            c = new RFclient(tmp_client);
            c.start();
				clientlist.add(c);
				System.out.println("building connection"+tmp_client);
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
    public void initial(DeviceInfo deviceInfo){
        this.deviceInfo = deviceInfo;		
    } 
	public int getIndex(String ID){
		for(int i=0;i<clientlist.size();i++){
			c = clientlist.get(i);
			if(ID.equals(c.arID))
				return i;
		}
		return -1;
	}
    public boolean controll(String ID,String[] cmd){
		System.out.printf("int RF : deviceId=%s : %s %s\n",ID,cmd[0],cmd[1]);
		int index = getIndex(ID);  
		if(index==-1)return false;
		boolean sendcmd;
		if(cmd[1].equals("off"))
            sendcmd = false;
        else if(cmd[1].equals("on"));
            sendcmd = true;
		c = clientlist.get(index);
		c.sendLight(sendcmd);
		/*
		String arID = deviceInfo.getArdIDFromMap(ID);
        String type = deviceInfo.getTypeFromMap(ID);
        String flag;//p or i
        String act;
        if(type.equals("Light")){
            flag="p";
            if(cmd[1].equals("off"))
                act="0";
            else if(cmd[1].equals("on"));
                act="1";
        }*/
		return true;
    }
    
}
