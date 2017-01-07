package com.test;

import java.lang.reflect.Field;

public class TestIntegerCache {
	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		int a = 10;
		int b = 20;
		t(a, b);
		System.out.println(String.format("a===============%d", a));
		System.out.println("a===============%d"+b);
	}
	
	static void t(int a, int b) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		Class clz = Integer.class.getDeclaredClasses()[0];
		System.out.println("clz======="+clz);
		Field field = clz.getDeclaredField("cache");
		System.out.println("field======="+field);
		field.setAccessible(true);
		Integer[] ii = (Integer[]) field.get(clz);
		ii[128 + a] = 100;
		ii[128 + b] = 200;
//		field[128+a] = 100;
	}
}
