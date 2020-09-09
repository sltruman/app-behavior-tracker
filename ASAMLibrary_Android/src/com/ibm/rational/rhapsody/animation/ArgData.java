/*********************************************************************
	Component	: anim
	Model Element	: ArgData
	Licensed Materials - Property of IBM
	(c) Copyright IBM Corporation 2008. All Rights Reserved.	
*********************************************************************/

package com.ibm.rational.rhapsody.animation;

import java.io.Serializable;
//## operation ArgData(Class,String,String) 
import java.lang.Class;

//----------------------------------------------------------------------------
// com/ibm/rational/rhapsody/animation/ArgData.java                                                                  
//----------------------------------------------------------------------------

//## package com::ibm::rational::rhapsody::animation 


/**
This class contains arguments data (for serialization).
*/
//## class ArgData 
@SuppressWarnings("serial")
public class ArgData implements Serializable {
    
    /**
    The class (or type) of the argument.
    */
    protected Class<?> argClass;		//## attribute argClass 
    protected String argClassName;
    /**
    The name of the argument.
    */
    protected String argName;		//## attribute argName 
    
    /**
    String representation of the argument value.
    */
    protected String argValue;		//## attribute argValue 
    
    
    // Constructors
    
    // Argument Class argClass : 
    /**
    The class\type of argument.
    */
    // Argument String argName : 
    /**
    The argument name.
    */
    // Argument String argValue : 
    /**
    String representation of the argument value.
    */
    //## operation ArgData(Class,String,String) 
    public  ArgData(Class<?> argClass, String argName, String argValue) {
        //#[ operation ArgData(Class,String,String) 
        this.argClass = argClass;
        this.argName = argName;
        this.argValue = argValue;
        //#]
    }
    public  ArgData() {
    }
    
    public Class<?> getArgClass() {
        return argClass;
    }
    
    public String getArgClassName() {
    	return argClassName;
    }
    
    public void setArgClass(Class<?> p_argClass) {
        argClass = p_argClass;
    }
    
    public void setArgClassName(String argClassName) {
    	this.argClassName = argClassName;
    }
    
    public String getArgName() {
        return argName;
    }
    
    public void setArgName(String p_argName) {
        argName = p_argName;
    }
    
    public String getArgValue() {
        return argValue;
    }
    
    public void setArgValue(String p_argValue) {
        argValue = p_argValue;
    }
}

