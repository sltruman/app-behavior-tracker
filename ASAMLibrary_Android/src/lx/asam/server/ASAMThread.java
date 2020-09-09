package lx.asam.server;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.ibm.rational.rhapsody.animation.ArgData;

import lx.asam.common.Common;
import lx.asam.common.Flag;
import lx.asam.common.Log;
import lx.org.json.JSONException;
import lx.org.json.JSONObject;

public class ASAMThread {
	private static Map<Integer,Process> processes = new HashMap<Integer,Process>();
	
	public static void main(String[] args) throws InterruptedException, UnknownHostException, IOException {
		System.gc();
		
		MessageList msgList = new MessageList();
		
		try {
			while(true) {
				JSONObject msg = msgList.popFront();
				dispose(msg);
			}
		} catch(Exception e) {
			Log.e(e.getMessage());
		}
	}

	private static void dispose(JSONObject msg) throws IOException {
		String method = null;
		String type = null;
		String code = null;
		ArgData[] args = null;
		
		try {
			int processID = msg.getInt("ProcessID");
			int threadID = msg.getInt("ThreadID");
			int flag = msg.getInt("Flag");
			
			Process p = null;
			
			if(processes.containsKey(processID)) {
				p = processes.get(processID);
			} else {
				p = new Process(processID);
				processes.put(processID, p);
			}

			Thread t = p.getThread(threadID);
			
			switch (flag) {
			case Flag.AnimClass:
				type = msg.getString("Class");
				t.staticObject(type,type);
				break;
			case Flag.AnimClass_notifyMethodEntered:
				code = msg.getString("HashCode");
				method = msg.getString("Method");
				args = Common.getArgs(msg);
				t.staticCall(code,method,args);
				break;
			case Flag.AnimClass_notifyMethodExit:
				t.ret();
				break;
			case Flag.AnimInstance_notifyConstructEntered:
				code = msg.getString("HashCode");
				type = msg.getString("Class");
				args = Common.getArgs(msg);
				t.newObject(code,type, args);
				break;
			case Flag.AnimInstance_notifyMethodEntered:
				code = msg.getString("HashCode");
				method = msg.getString("Method");
				args = Common.getArgs(msg);
				t.call(code,method,args);
				break;
			case Flag.AnimInstance_notifyMethodExit:
				t.ret();
				break;
			default:
				break;
			}

		} catch(JSONException e) {
			Log.e(e.getMessage());
		}
	}
}
