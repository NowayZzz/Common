package zp.com.test;

class HelloA {

    public HelloA(String s) {
        System.out.println("HelloA");
    }
    
    { System.out.println("I'm A class"); }
    
    static { System.out.println("static A"); }

}

public class HelloB extends HelloA {
	
    public HelloB() {
    	super("1");
        System.out.println("HelloB");
    }
    
    { System.out.println("I'm B class"); }
    
    static { System.out.println("static B"); }
    
    public static void main(String[] args) {

        new HelloB();
    }
}

