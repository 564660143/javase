package thread.automatic ;

import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;
/**
 * 使用lock保证线程安全
 * @author Administrator
 *
 */
import java.util.concurrent.locks.Lock ;
import java.util.concurrent.locks.ReentrantLock ;

public class SequenceByLock {
	
	private int	num ;
	Lock		lock	= new ReentrantLock() ;
	
	public int getNext() {
		// lock.lock();
		// 多线程情况下,这行代码存在线程安全性问题
		int temp = num++ ;
		// lock.unlock();
		return temp ;
	}
	
	public static void main(String [] args) {
		// 线程池的方式实现线程
		ExecutorService executorService = Executors.newFixedThreadPool(10) ;
		SequenceByLock seq = new SequenceByLock() ;
		for (int i = 0; i < 3; i++) {
			// 内部类的方式实现Runnable接口
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					while (true) {
						System.out.println(Thread.currentThread().getName() + ":" + seq.getNext()) ;
					}
				}
			}) ;
		}
		
	}
	
}
