/*********************************************************************
	Component	: anim
	Model Element	: AnimInstance
	Licensed Materials - Property of IBM
	(c) Copyright IBM Corporation 2008. All Rights Reserved.	
 *********************************************************************/

package com.ibm.rational.rhapsody.animation;

import java.lang.Class;

import lx.asam.common.Common;
import lx.asam.common.Datagram;
import lx.asam.common.Flag;
import lx.asam.server.MessageList;
import lx.org.json.JSONObject;

public class AnimInstance {
	private int processID = 0;
	private String hashCode = null;
	private String className = null;
	
	@Override
	protected void finalize() throws Throwable {
		// TODO 自动生成的方法存根
		super.finalize();

		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("HashCode", hashCode);
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimInstance_finalize);
		msg.put("Method", "finalize");
		MessageList.pushBack(msg);
	}
	
	public AnimInstance(Animated userInstance) {
		hashCode = Integer.toHexString(this.hashCode());
		className = userInstance.getAnimClass().getUserClass().getName();
		processID = Datagram.getpid();
		
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimInstance);
		MessageList.pushBack(msg);
	}
	
	public void notifyConstructorEntered(Class<?> declaringClass, ArgData[] args) {
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("HashCode", hashCode);
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimInstance_notifyConstructEntered);
		Common.putArgs(msg, args);
		MessageList.pushBack(msg);
    }
	
	public void notifyMethodEntered(String name, ArgData[] args) {
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("HashCode", hashCode);
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimInstance_notifyMethodEntered);
		msg.put("Method", name);
		Common.putArgs(msg, args);
		MessageList.pushBack(msg);
	}
	
	public void notifyMethodExit() {
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("HashCode", hashCode);
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimInstance_notifyMethodExit);
		MessageList.pushBack(msg);
	}
	
	// ## operation animToString(Object)
	public static String animToString(Object o) {
		if(o == null)
			return "null";
		return o.toString();
	}
}
