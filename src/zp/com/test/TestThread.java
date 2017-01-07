package zp.com.test;

public class TestThread   implements Runnable{

	 int count = 100;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestThread tt = new TestThread();
		new Thread(tt,"1").start();
		new Thread(tt,"2").start();
		new Thread(tt,"3").start();
		new Thread(tt,"4").start();
		new Thread(tt,"5").start();
	}

	@Override
	public synchronized void run() {
		  for (int i = 0; i < 10; i++) {
			 try {
				 System.out.println(Thread.currentThread().getName()+"---------"+--count);
				Thread.sleep(11);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//System.out.println(Thread.currentThread().getName()+"---"+i);
		}
	}

	
}
