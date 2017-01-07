package com.util.辅助类;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public abstract class BaseModel implements Serializable{
	
	private static final long serialVersionUID = 3688095348055221994L;

	public final String toString(){
        StringBuffer results = new StringBuffer();
        Class<? extends BaseModel> clazz = getClass();
        results.append(getClass().getName() + "\n");
        Field fields[] = clazz.getDeclaredFields();
        try
        {
            AccessibleObject.setAccessible(fields, true);
            for(int i = 0; i < fields.length; i++)
                results.append("\t" + fields[i].getName() + "=" + fields[i].get(this) + "\n");

        }
        catch(Exception exception) { }
        return results.toString();
    }
}
