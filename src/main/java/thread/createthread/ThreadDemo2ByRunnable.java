package thread.createthread;

/**
 * 实现Runnable接口的线程实现方式
 * @author Administrator
 *
 */
public class ThreadDemo2ByRunnable implements Runnable {
	
	@Override
	public void run() {
		System.out.println("实现Runnable接口的线程实现方式------") ;
	}
	
	public static void main(String [] args) {
		new Thread(new ThreadDemo2ByRunnable()).start();
	}
	
}
