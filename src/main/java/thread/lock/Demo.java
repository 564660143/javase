package thread.lock;

import java.util.concurrent.locks.Lock ;

public class Demo {
	private Lock lock = new MyLock();
	
	public synchronized void  a() {
		lock.lock();
		System.out.println("aaa") ;
		b();
		lock.unlock();
	}
	
	public synchronized void  b() {
		lock.lock();
		System.out.println("bbb") ;
		lock.unlock();
	}
	
	public static void main(String [] args) {
		Demo demo = new Demo();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				demo.a();
			}
		}).start();
		
		
	}
	
}
