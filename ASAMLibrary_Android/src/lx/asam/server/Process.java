package lx.asam.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class Process {
	private Map<Integer,Thread> list = new HashMap<Integer,Thread>();
	
	private Socket socket = null;
	private OutputStream output = null;
	private int number = 1;
	
	public Process(int pid) throws IOException {
		socket = new Socket("10.0.2.2",60001);
		output = socket.getOutputStream();
		output.write(("P" + pid + "\n").getBytes("GBK"));
		Thread main = new Thread(0,"T0","T0",output);
		main.staticObject("T0","Entry");
		list.put(0, main);
	}
	
	public Thread getThread(int tid) throws UnsupportedEncodingException, IOException {
		Thread t = null;
		
		if(list.containsKey(tid))
			t = list.get(tid);
		else {
			t = new Thread(number,"T" + tid,"T0",output);
			list.put(tid,t);
			t.newThread("T" + tid,0);
			number++;
		}
		return t;
	}
}
