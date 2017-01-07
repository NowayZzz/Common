package com.util.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Test序列化  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8726676397220013830L;
	
	public String id;
	public static String staticStr;
	private String username;
	private String passwd;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public static void main(String[] args) {
		
		read();
	}
	
	static void write(){
		Test序列化 loginUser = new Test序列化();
		loginUser.id="1";
		loginUser.staticStr="static";
		loginUser.setUsername("50");
		loginUser.passwd="20";

		try {
			FileOutputStream fs = new FileOutputStream("E:\\foo.txt");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(loginUser);
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	static void read(){
		 try {
		   FileInputStream fs = new FileInputStream("E:\\foo.txt");//("foo.ser");
		   ObjectInputStream ois = new ObjectInputStream(fs);
		   Test序列化 loginUser = (Test序列化) ois.readObject();
		   System.out.println(loginUser.getUsername() + "----"+loginUser.id+"-----------"+loginUser.staticStr+"---------"
		     + loginUser.getPasswd());
		   ois.close();
		  } catch (Exception ex) {
		   ex.printStackTrace();
		  }
	}
}