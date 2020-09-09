package lx.asam.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Stack;

import lx.asam.common.Common;

import com.ibm.rational.rhapsody.animation.ArgData;

public class Thread {
	private Stack<String> callStack = new Stack<String>();
	private OutputStream output = null;
	private int id = 0;
	String tid = null;
	
	@Override
	protected void finalize() throws Throwable {
		callStack.pop();
		super.finalize();
	}
	
	public Thread(int id,String name,String parent, OutputStream output) {
		this.id = id;
		this.tid = name;
		this.output = output;
		callStack.push(String.valueOf(parent));
	}
	
	public void ret() {
		callStack.pop();
	}
	
	public String current() {
		return callStack.peek();
	}
	
	public void newThread(String name,int parentID) throws UnsupportedEncodingException, IOException {
		String s = "@$@$@$@$" + "/" + name + ":Thread\n";
		output.write(s.getBytes("GBK"));

		String last = current();
		callStack.push(name);
		s = last + "[," + parentID + "]:>" + name + ".new()\n";
		output.write(s.getBytes("GBK"));
	}
	
	public void newObject(String object,String type,ArgData[] args) throws UnsupportedEncodingException, IOException {
		String s = "@$@$@$@$" + "/" + object + ":" + type + "\n";
		output.write(s.getBytes("GBK"));
		call(object,"new",args);
	}
	
	public void call(String object,String method, ArgData[] args) throws UnsupportedEncodingException, IOException {
		String last = current();
		callStack.push(object);
		String s = last + "[," + id + "]:" + (last.equals(object) ? "" : object + ".") + method + "(" + Common.toScript(args) + ")\n";
		output.write(s.getBytes("GBK"));
	}

	public void staticCall(String type,String method, ArgData[] args) throws UnsupportedEncodingException, IOException {
		String last = current();
		callStack.push(type);
		String s = last + "[," + id + "]:" + (last.equals(type) ? "" : type + ".") + method + "(" + Common.toScript(args) + ")\n";
		output.write(s.getBytes("GBK"));
	}
	
	public void staticObject(String object,String type) throws UnsupportedEncodingException, IOException {
		String s = "@$@$@$@$" + object + ":" + type + "[a]\n";
		output.write(s.getBytes("GBK"));
	}
	
	public void deleteObject(String object) throws UnsupportedEncodingException, IOException {
		String s = current() + ":" + object + ".destroy()\n";
		output.write(s.getBytes("GBK"));
	}

}
