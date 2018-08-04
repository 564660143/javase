package thread.concurrentutil ;

import java.util.ArrayList ;
import java.util.List ;
import java.util.concurrent.CountDownLatch ;

/**
 * CountDownLatch并发工具类demo
 * 多线程并发求和
 * @author Administrator
 *
 */
public class CountDownLatchDemo {
	
	private int [] nums ;
	
	public CountDownLatchDemo(Integer length) {
		nums = new int [length] ;
	}
	
	/**
	 * 计算每一行的数字之和,存放到nums数组的指定位置
	 * @param line,1,2,3以逗号分割的字符串
	 */
	public void lineSum(String line, int index, CountDownLatch latch) {
		String [] nos = line.split(",") ;
		int total = 0 ;
		for (String num : nos) {
			total += Integer.parseInt(num) ;
		}
		System.out.println(Thread.currentThread().getName() + "线程执行计算" + line + "结果为:" + total) ;
		nums[index] = total ;
		/* 每调用一次CountDownLatch的countDown方法,计数器将会减1,计数器减为0时,
		 * 将唤醒使用CountDownLatch多的await方法进入休眠的线程,进入后续操作 */
		latch.countDown() ;
	}
	
	/**
	 * 汇总求和
	 */
	public void sum() {
		System.out.println("汇总线程开始执行----") ;
		int sum = 0 ;
		for (int i : nums) {
			sum += i ;
		}
		System.out.println("汇总结果为: " + sum) ;
	}
	
	public static void main(String [] args) {
		/************初始化测试数据************/
		List<String> list = new ArrayList<>() ;
		list.add("1,2,3,4,5") ;
		list.add("11,22,34,34,33") ;
		list.add("12,23,34,45,56") ;
		list.add("666,666") ;
		/************初始化测试数据************/
		// 生成一个与数组个数对应的CountDownLatch计数器
		CountDownLatch latch = new CountDownLatch(list.size()) ;
		CountDownLatchDemo demo = new CountDownLatchDemo(list.size()) ;
		// 多线程计算
		for (int i = 0; i < list.size(); i++) {
			final int j = i ;
			new Thread() {
				
				@Override
				public void run() {
					demo.lineSum(list.get(j), j, latch) ;
				}
			}.start() ;
		}
		
		try {
			// 调用await方法使main线程处于等待状态,当CountDownLatch计数为0使,才会重新唤醒main进程
			latch.await() ;
		} catch (InterruptedException e) {
			e.printStackTrace() ;
		}
		// 汇总求和
		demo.sum() ;
	}
	
}
