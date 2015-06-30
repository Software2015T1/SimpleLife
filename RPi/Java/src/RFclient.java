import java.util.*;
import java.io.*;
import java.net.*;

public class RFclient extends Thread{
	Socket socket;
	BufferedReader in;
	PrintWriter out;
	public String arID;
	boolean flag;
	private volatile boolean running = true;
	String readStr;
	public RFclient(Socket c){
		socket = c;
		try{
			in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			out = new PrintWriter(c.getOutputStream());
		}catch (IOException e){
				e.printStackTrace();
		}
	}
	public void sendLight(String cmd){
		out.println(cmd);
		out.flush();
	}
	@Override
	public void run(){
		while(running){
         System.out.println("test");
			try{
				readStr = in.readLine(); 
            System.out.println("read"+readStr);
			}catch (IOException e){
				e.printStackTrace();
			}
				String[] cmdArray = readStr.split(" ");
				if(cmdArray[0].equals("id"))
					this.arID = cmdArray[1];
				System.out.println("end listen");
				System.out.println("from client:"+readStr); 
			
		}
	}
	void terminate() {
        running = false;
    }
	
}
