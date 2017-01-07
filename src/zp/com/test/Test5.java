package zp.com.test;



public class Test5 {
	
	static int j = 10;
	
	public static void main(String[] args) throws InterruptedException {
		Test5 t= new Test5();
		Thread1 t1 = t.new Thread1();
		Thread2 t2 = t.new Thread2();
		for (int i = 0; i < 10; i++) {
			new Thread(t2).start();
			
		}
		for (int i = 0; i < 10; i++) {
			new Thread(t1).start();
			
		}
		
		System.out.println(j);
	}
	
	void inc() throws InterruptedException{
		j++;
		Thread.sleep(1);
		System.out.println(Thread.currentThread().getName()+"+++"+j);
	}
	void dec() throws InterruptedException{
		j--;
		Thread.sleep(1);
		System.out.println(Thread.currentThread().getName()+"---"+j);
	}
	
	class Thread1 implements Runnable{

		@Override
		synchronized public void run() {
			try {
				inc();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	class Thread2 implements Runnable{
		
		@Override
		synchronized public void run() {
			try {
				dec();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}

