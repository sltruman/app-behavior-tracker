package lx.asam.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import lx.asam.common.Datagram;
import lx.asam.common.Log;
import lx.org.json.JSONObject;

public class MessageList {
	private DatagramSocket socket = null;
	private DatagramPacket packet = null;
	
	public MessageList() {
		try {
			socket = new DatagramSocket(60001);
			packet = new DatagramPacket(new byte[4 * 1024],4 * 1024);
		} catch (SocketException e) {
			Log.e(e.getMessage());
		}
	}
	
	public JSONObject popFront() {
		JSONObject msg = null;
		
		try {
			socket.receive(packet);
			msg = new JSONObject(new String(packet.getData()));
		} catch (IOException e) {
			Log.e(e.getMessage());
		}
		
		return msg;
	}
	
	public static void pushBack(JSONObject msg) {
		try {
			byte[] data = msg.toString().getBytes("GBK");
			
			if(!Datagram.send(data)) {
				Log.e("Datagram.send() failed");
			}
		} catch (IOException e) {
			Log.e(e.getMessage());
		}
	}
}
