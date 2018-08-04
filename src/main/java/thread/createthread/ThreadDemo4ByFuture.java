package thread.createthread;

import java.util.concurrent.Callable ;
import java.util.concurrent.ExecutionException ;
import java.util.concurrent.FutureTask ;

/**
 * 有返回值的线程
 * @author Administrator
 *
 */
public class ThreadDemo4ByFuture implements Callable<Integer> {

	@Override
	public Integer call() throws Exception {
		System.out.println("带返回值的线程---------") ;
		return 1 ;
	}
	
	public static void main(String [] args) throws Exception {
		ThreadDemo4ByFuture tt = new ThreadDemo4ByFuture();
		FutureTask<Integer> future = new FutureTask<>(tt);
		Thread thread = new Thread(future);
		thread.start();
		System.out.println("--------------") ;
		System.out.println(future.get());
	}
	
}
