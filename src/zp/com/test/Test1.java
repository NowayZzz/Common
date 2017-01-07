package zp.com.test;

import java.util.ArrayList;
import java.util.List;

public class Test1 implements Cloneable{
	
	private int id;
	
	private String name;
	
	private int age;
	
	public int getId() {
		System.out.println("============="+this.getClass());
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}
	
}
