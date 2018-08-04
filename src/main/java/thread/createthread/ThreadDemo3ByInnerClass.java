package thread.createthread;


public class ThreadDemo3ByInnerClass {
	
	public static void main(String [] args) {
		new Thread(){
			@Override
			public void run() {
				System.out.println("Thread 内部类方式实现线程----") ;
			}
		}.start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Runnable 内部类方式实现线程----") ;				
			}
		}).start();
		
	}
	
}
