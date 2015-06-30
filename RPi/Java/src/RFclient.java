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
    private RF rf;
	public RFclient(Socket c,RF rf){
		socket = c;
        this.rf = rf;
		try{
			in = new BufferedReader(new InputStreamReader(c.getInputStream()));
			out = new PrintWriter(c.getOutputStream());
		}catch (IOException e){
				e.printStackTrace();
		}
	}
	public void sendLight(String cmd){
        System.out.println("success");
		out.println(cmd);
		out.flush();
	}
	@Override
	public void run(){
		while(running){
         System.out.println("client read");
			try{
				readStr = in.readLine(); 
			}catch (IOException e){
				e.printStackTrace();
			}
			String[] cmdArray = readStr.split(" ");
			if(cmdArray[0].equals("id"))
				this.arID = cmdArray[1];
            else if(cmdArray[0].equals("MOTION01")){
                this.sendLight(cmdArray[1]);
            }
			System.out.println("from client:"+readStr); 
		}
	}
	void terminate() {
        running = false;
    }
	
}
