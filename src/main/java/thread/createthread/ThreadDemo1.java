package thread.createthread;

/**
 * 继承Thread类实现线程
 * @author Administrator
 *
 */
public class ThreadDemo1 extends Thread {

	@Override
	public void run() {
		System.out.println("线程执行中------") ;
	}
	
	public static void main(String [] args) {
		new ThreadDemo1().start();
	}
	
}
