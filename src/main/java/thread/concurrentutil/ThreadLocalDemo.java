package thread.concurrentutil ;

/**
 * ThreadLocal使用测试
 * @author Administrator
 *
 */
public class ThreadLocalDemo {
	
	private ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>() {
		
		@Override
		protected Integer initialValue() {
			return new Integer(0) ;
		}
	} ;
	
	/**
	 * 每个线程调用getNext方法时,获取的都是从0开始递增计数器,且彼此之间互不影响
	 * @return
	 */
	public Integer getNext() {
		int value = threadLocal.get() ;
		threadLocal.set(value + 1) ;
		return value ;
	}
	
	public static void main(String [] args) {
		ThreadLocalDemo demo = new ThreadLocalDemo() ;
		// 通过匿名内部类的方式实现两个线程
		new Thread() {
			
			@Override
			public void run() {
				
				while (true) {
					System.out.println(Thread.currentThread().getName() + ":" + demo.getNext()) ;
					try {
						Thread.sleep(1000) ;
					} catch (InterruptedException e) {
						e.printStackTrace() ;
					}
				}
			}
		}.start() ;
		
		new Thread() {
			
			@Override
			public void run() {
				System.err.println(this) ;
				while (true) {
					System.out.println(Thread.currentThread().getName() + ":" + demo.getNext()) ;
					try {
						Thread.sleep(2000) ;
					} catch (InterruptedException e) {
						e.printStackTrace() ;
					}
				}
			}
		}.start() ;
		
	}
	
}
