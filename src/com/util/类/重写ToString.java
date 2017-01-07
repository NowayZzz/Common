package com.util.类;

import java.io.Serializable;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

public class 重写ToString implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3693670255483937692L;

	public final String toString() {
		StringBuffer results = new StringBuffer();
		Class<? extends 重写ToString> clazz = getClass();
		results.append(getClass().getName() + "\n");
		Field fields[] = clazz.getDeclaredFields();
		try {
			AccessibleObject.setAccessible(fields, true);
			for (int i = 0; i < fields.length; i++)
				results.append("\t" + fields[i].getName() + "=" + fields[i].get(this) + "\n");

		} catch (Exception exception) {
		}
		return results.toString();
	}
}
