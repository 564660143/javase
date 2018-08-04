package thread.lock ;

import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
import java.util.concurrent.atomic.AtomicInteger ;
import java.util.concurrent.locks.Lock ;
/**
 * 原子类保证线程安全性问题
 * @author Administrator
 *
 */
public class Sequence2 {
	private int  num;
	private Lock lock = new MyLock();
	public int getNext(){
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		lock.lock();
		int temp = num++;
		lock.unlock();
		return temp;
	}
	
	public static void main(String [] args) {
		// 线程池的方式实现线程
		ExecutorService executorService = Executors.newFixedThreadPool(10);	
		Sequence2 seq = new Sequence2();
		for(int i = 0; i < 3; i++){
			// 内部类的方式实现Runnable接口
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					while (true) {
						System.out.println(Thread.currentThread().getName() + ":"  + seq.getNext()) ;
					}
					
				}
			});
		}
		
	}
	
	
}
