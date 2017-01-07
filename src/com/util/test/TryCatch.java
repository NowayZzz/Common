package com.util.test;

import java.io.IOException;

public class TryCatch {
	public void doSomething() throws IOException {
//		System.out.println(4<<4);
		int i = 1;
		switch (i) {
		case 2:
			System.out.println("2");
		case 1:
			System.out.println("1");
		case 3:
			System.out.println("3");
		case 4:
			System.out.println("4");
			break;
		case 5:
			System.out.println("5");
		}
	}

	public static void main(String[] args) {
//		TryCatch et = new TryCatch();
//		try {
//			et.doSomething();
//		} catch (IOException e) {
//			//
//		} catch (Exception e) {
//			//
//		}
		Integer[] i = new Integer[5];
		System.out.println(i[5]);
	}
}