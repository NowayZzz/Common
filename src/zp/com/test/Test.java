package zp.com.test;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;


public class Test {
	
	private int id;
	
	private String name;
	
	static int d = 2;
	
	public static void main(String[] args) throws UnsupportedEncodingException, ParseException, CloneNotSupportedException {
		/*Test1 t1 = new Test1();
		t1.setId(0);
		t1.setName("123");
		t1.setAge(23);
		Test1 t2=  (Test1) t1.clone();
		setAge(t1);
		System.out.println(t1.getAge());
		System.out.println(t2.getAge());
		int i = 0;
		i=i;*/
		/*Son son = new Son();
		son.setId(1);
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "zp");
		map.put("pswd", "123465");
		map.put("nnnn", "1");
		
		Set<String> s = map.keySet();
		for (String string : s) {
			System.out.println(string+"-------"+map.get(string));
		}*/
		
		/*Student s = new Student();
		s.setId("123");
		if(s.getAge() == 1){
			System.out.println("-------------");
		}else{
			System.out.println("-------------1111111111");
		}*/
		
//		HashMap<String, String> m = new HashMap<String, String>();
//		m.put("我", "123");
//		String s = "我";
//		System.out.println(m.get(s));
		
		/*String s = "xxx{0}xxx{1}xxx";
		for(int j = 0 ; j < 2 ; j++){
			s = s.replace("{"+j+"}","XXX");
		}
		System.out.println(s);*/
		
		/*Date d = new Date(123456);
		System.out.println(d+"'");*/
		
		/*Test1 t1 = new Test1();
		t1.setId(12);
		t1.getId();
		 System.out.println(new BigDecimal(4/7).setScale(4,BigDecimal.ROUND_HALF_UP));*/
		 /*System.out.println(new Date(0));
		 
		 BigDecimal bd = new BigDecimal(99.96);
		 final int f = 1;
		 System.out.println(d);
		 d = 3;
		 System.out.println(d);
		 System.out.println(99.96* 10000);*/
		 
		/*List<Test1> list = new ArrayList<Test1>();
		if(1==1){
			Test1 t1 = new Test1();
			t1.setId(1);
			t1.setName("111");
			t1.setAge(11);
			list.add(t1);
		}
		
		if(true){
			Test1 t1 = new Test1();
			t1.setId(2);
			t1.setName("222");
			t1.setAge(22);
			list.add(t1);
		}
		
		System.out.println(list);*/
		
		/*String[] s = {"1","2"};
//		System.out.println(stringArrToString(s));
		System.out.println(Arrays.toString(s));
		
		Set<Test1> set = new HashSet<Test1>();
		for (Test1 test1 : set) {
			set.remove(test1);
		}*/
		//texes009.put("fv80",fv80);
		//String fk80=request.getParameter("fk80");if(fk80==null)fk80="";
//		System.out.println(0.00==0);
//		for(int i=1;i<442;i++){
////			String value = "fv"+i;
//			System.out.print("?,");
////			System.out.print("texes009.put(\"fv"+i+"\",fv"+i+");");
//			if(i%70==0)System.out.println();
//		}
		
		/*String snote = "adf";
		snote = new String(snote.getBytes("ISO-8859-1"),"GBK"); 
		//System.out.println(new Date().getTime());
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.YEAR, -1);
		//System.out.println(cal.getTime());
		Test1 t1 = new Test1();
		t1.setId(1);
		t1.setName("111");
		t1.setAge(11);
		Test1 t2 = new Test1();
		t2.setId(1);
		t2.setName("111");
		t2.setAge(11);
		long a = 403;
		long b = 403;
		System.out.println(a==b);
		System.out.println(t2.getAge()==t2.getAge());*/
		
		Date startTimeTjls = new SimpleDateFormat("yyyyMMdd").parse("20151230");//天津绿色启用日期
		System.out.println("1H123".startsWith("H"));
	}
	
	public static Test1 setAge(Test1 test1){
		test1.setAge(2);
		return test1;
	}
	public static Test1 formatteTestToTest1(Object obj){
		if (obj instanceof Test) {
			Test t = (Test) obj;
		}
		Test t = (Test) obj;
		Test1 t1 = new Test1();
		t1.setId(t.getId());
		t1.setName(t.getName());
		t1.setAge(25);
		return t1;
	}
	
	public static String stringArrToString(String[] strArr){
		StringBuffer sb = new StringBuffer();
		for (String string : strArr) {
			sb.append(string+"\n");
		}
		return sb.toString();
	}
	
	public Test(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public int getId() {
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
	
	private static List<Node> toTree(Set<Node> set, List<Node> nodes, String poid) {
	       for(Node node : set){
	           if(node.getPoid().equals(poid)){
	              List<Node> children = new ArrayList<Node>();
	              node.setChildren(toTree(set,children,node.getNodeid()));
	              nodes.add(node);
	              set.remove(node);
	           }
	       }
	       return nodes;
	    }

}
