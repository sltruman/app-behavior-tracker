
package com.ibm.rational.rhapsody.animation;

import java.lang.reflect.Field;

import com.ibm.rational.rhapsody.animcom.animMessages.AnimAttributes;
import com.ibm.rational.rhapsody.animcom.animMessages.AnimRelations;


public interface Animated {
    
    /**
    Adds the attributes of the animated instance to the msg.
    */
    //## operation addAttributes(AnimAttributes) 
    void addAttributes(AnimAttributes attributes);
    
    /**
    Adds the realtions of the animated instance to the msg.
    */
    //## operation addRelations(AnimRelations) 
    void addRelations(AnimRelations attributes);
    
    /**
    Creates and returns or just returns the AnimInstance instance of this Animatable.
    */
    //## operation animInstance() 
    AnimInstance animInstance();
    
    /**
    return the anim class of the animated instance. required in order to get the correct AnimClass instance during construction.
    */
    //## operation getAnimClass() 
    AnimClass getAnimClass();
    
    //## operation getFieldValue(Field,Object) 
    Object getFieldValue(Field f, Object userInstance);
    
}

