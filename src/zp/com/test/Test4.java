package zp.com.test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {
	public static void main(String[] args) throws CloneNotSupportedException {
//		Test1 t1 = new Test1();
//		t1.setId(1);
//		t1.setAge(20);
//		t1.setName("123");
//		Test1 t2 = (Test1) t1.clone();
//		System.out.println(t2.getName());
//		Field[] fields2 = t1.getClass().getDeclaredFields();
//		for (int i = 0; i < fields2.length; i++) {
//			System.out.println("-----------"+fields2[i]);
//		}
//		int h = 15;
//		int hcenter = h/2;
//		for (int i = 1; i < h; i++) {
//			if(i<=hcenter+1){
//				for (int j = 1; j <= hcenter+1-i; j++) {
//					System.out.print(" ");
//				}
//				for (int i_ = 0; i_ < 2*i-1; i_++) {
//					System.out.print("*");
//				}
//				System.out.println();
//			}
//			if(i>hcenter){
//				for (int j = 1; j < i-hcenter+1; j++) {
//					System.out.print(" ");
//				}
//				for (int i_ = 1; i_ < 2*(h-i); i_++) {
//					System.out.print("*");
//				}
//				System.out.println();
//			}
//		}
//		
//		System.out.println(952.785/1.8);
//		
//		Integer i = 1;
//		Integer j = 1;
//		System.out.println(i==j);
//		
//		Integer x = 200;
//		Integer y = 200;
//		Integer z = x;
//		System.out.println(x==y);
//		
//		System.out.println("------------");
//		String s1 = "123";
//	       String s2 = "123";
//	       System.out.println(s1==s2);
//	       String sn1 = new String("123");
//	       String sn2 = new String("123");
//	       System.out.println(sn1==sn2);
		
		/*String s1 = "123";
	       String s2 = "123";
	       
	       String sn1 = new String("123");
	       String sn2 = new String("123");
	       
	       StringBuffer sb1 = new StringBuffer("123");
	       StringBuffer sb2 = new StringBuffer("123");
	       
	       Set set1 = new HashSet<Object>();
	       Set set2 = new HashSet<Object>();
	       Set set3 = new HashSet<Object>();
	       set1.add(s1);
	       set1.add(s2);
	       set2.add(sn1);
	       set2.add(sn2);
	       set3.add(sb1);
	       set3.add(sb2);
	       
	       System.out.println(s1==s2);
	       System.out.println(sn1==sn2);
	       System.out.println(set1.size());
	       System.out.println(set2.size());
	       System.out.println(set3.size());
	       System.out.println(sn1.equals(sn2));
	       System.out.println(sb1.equals(sb2));*/
		
//		String s = "apple";
//		String b = s+"s";
//		System.out.println(s);
		
		System.out.println(2 == (Integer)2);
		String[] s = {"1","2"};
		/*HashMap<String, Integer> wrapItems = new HashMap<String, Integer>();
			Pattern p = Pattern.compile("\\s{1,}");
			Matcher m = p.matcher("Fasda123 50 Fasda123 50");
			String sparecode = m.replaceAll(" ");
			
			p = Pattern.compile("([a-zA-Z0-9]{6})[\\s|:]{1}(\\d{1,})", Pattern.CASE_INSENSITIVE);
			m = p.matcher(sparecode);
			
			while(m.find()){
				wrapItems.put(m.group(1), Integer.parseInt(m.group(2)));
			}
			for (String string : wrapItems.keySet()) {
				System.out.println(wrapItems.get(string));
			}*/

	}
}
