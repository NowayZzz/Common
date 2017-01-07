package zp.com.test;

public class Test3 {

    String str = new String("good");

    char[] ch = { 'a', 'b', 'c' };

    public static void main(String args[]) throws InterruptedException {

        Test3 ex = new Test3();

        ex.change(ex.str, ex.ch);
        ex.change(ex.str, ex.ch);
        System.out.print(ex.str + " and ");

        System.out.print(ex.ch);

    }

    public void change(String str, char aa[]) throws InterruptedException {

        str = "test ok";
        System.out.println(Thread.currentThread());
        Thread.sleep(500);
        System.out.println("------------"+str);
        aa[0] = 'g';

    }
}
