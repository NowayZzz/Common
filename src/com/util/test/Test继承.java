package com.util.test;

public class Test继承 {
	public static void main(String[] args) {	//	主方法开始
		Child c = new Child();					//  实例化
	}
}

class Father {
	public Father() {
		System.out.println("父类无参构造函数");
	}

	public Father(String name) {
		System.out.println("父类有参构造函数");
	}
}

class Child extends Father {
	public Child() {
		//this("dd");
		System.out.println("子类无参构造函数");
	}

	public Child(String name) {
		super();
		System.out.println("子类有参构造函数");
	}
}