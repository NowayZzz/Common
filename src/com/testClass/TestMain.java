package com.testClass;

import java.util.BitSet;

public class TestMain {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException {
		Class<Child> clazz = Child.class;
//		clazz.getSuperclass().newInstance();
		if (clazz.getSuperclass().newInstance()  instanceof Father) {
			System.out.println("-----1------");
		};
		if (clazz.getSuperclass().newInstance()  instanceof Human) {
			System.out.println("-----------");
		};
		if (clazz.getSuperclass().newInstance()  instanceof Child) {
			System.out.println("-----2------");
		};

	}

}
