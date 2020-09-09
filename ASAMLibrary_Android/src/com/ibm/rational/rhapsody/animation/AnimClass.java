/*********************************************************************
	Component	: anim
	Model Element	: AnimClass
	Licensed Materials - Property of IBM
	(c) Copyright IBM Corporation 2008. All Rights Reserved.	
 *********************************************************************/

package com.ibm.rational.rhapsody.animation;

import java.lang.Class;


/**
 ProxyClasses. An instance of this class is created for every model class that can be instantiated. It contains static data for this class, and also manages the instances of this class.
 */
import lx.asam.common.Common;
import lx.asam.common.Datagram;
import lx.asam.common.Flag;
import lx.asam.server.MessageList;
import lx.org.json.JSONObject;

public class AnimClass {
	private Class<?> theClass = null;
	private String className = null;
	private int processID = 0;

	public AnimClass(String className, boolean isSingleton) {
		try {
			theClass = Class.forName(className);
			this.className = className;
		} catch (ClassNotFoundException e) {
			e.printStackTrace(System.err);
		}
		
		processID = Datagram.getpid();
		
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("Flag", Flag.AnimClass);
		msg.put("Class", className);
		MessageList.pushBack(msg);
	}

	public Class<?> getUserClass() {
		return theClass;
	}

	public void notifyMethodEntered(String name, ArgData[] args) {
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimClass_notifyMethodEntered);
		msg.put("Method", name);
		Common.putArgs(msg, args);
		MessageList.pushBack(msg);
	}

	public void notifyMethodExit() {
		JSONObject msg = new JSONObject();
		msg.put("ProcessID", processID);
		msg.put("ThreadID", Thread.currentThread().getId());
		msg.put("Class", className);
		msg.put("Flag", Flag.AnimClass_notifyMethodExit);
		MessageList.pushBack(msg);
	}
}
