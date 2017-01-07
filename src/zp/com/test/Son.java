package zp.com.test;


public class Son extends Father{
	private String sex;
	
	public Son() {
		super();
		System.out.println(super.getClass());
		// TODO Auto-generated constructor stub
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
}
