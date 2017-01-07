package zp.com.test;


import java.util.Map;
import java.util.TreeMap;

import org.nutz.trans.Trans;

public class TestMysql {
	
	static final String s = "123";
	static final Map m = new TreeMap<Integer, String>();
	public static void main(String[] args) {
		m.put("1", "1");
		m.put("222", "6");
		m.put("3", "3");
		m.put("4", "4");
		m.put("5", "5");
		new Runnable(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
			}};
//		Set<Integer> set = m.keySet();
//		FilePool pool = new NutFilePool("D:/tmp/myfiles", 20);
//		pool.createFile("122.png");
//		File f = new File("D:/tmp/myfiles/00/00/00/00/00/00/00/11.png");
//		System.out.println(f);
		//		for (int str : set) {
//			System.out.println(m.get(str));
//		}
//		Test1 t1 = new Test1();
//		t1.setId(1);
//		t1.setAge(20);
//		t1.setName("123");
//		System.out.println( Dumps.obj(t1));
//		R.random(3,5);
		/*try {
			try {
				throw new B();
			} catch (A a) {
				System.out.println("catch A");
				throw a;
			}
		} catch (B b) {
			System.out.println("catch B");
			return ;
		} finally {
			System.out.println("hello world!");
		}*/
	}
}
