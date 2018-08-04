package thread.createthread;

import java.util.concurrent.Executor ;
import java.util.concurrent.ExecutorService ;
import java.util.concurrent.Executors ;

public class ThreadDemo5ByThreadPool {
	
	public static void main(String [] args) {
		// 固定大小的线程池
//		ExecutorService executorService = Executors.newFixedThreadPool(10);
		// 自动管理大小的线程池
		ExecutorService executorService = Executors.newCachedThreadPool();

		for (int i = 0; i < 100; i++) {
			executorService.execute(
					new Runnable() {
						@Override
						public void run() {
							System.out.println(Thread.currentThread().getName() + "线程执行了----") ;
						}
					}
					);
			
		}
		
		executorService.shutdown();
		
	}
	
}
