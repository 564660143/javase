package thread.concurrentutil;

import java.util.concurrent.BrokenBarrierException ;
import java.util.concurrent.CyclicBarrier ;

class MenPai implements Runnable{
	// 名称
	private String name;
	// 到达光明顶所需要的时间
	private long time;
	// CyclicBarrier
	private CyclicBarrier cyclicBarrier;
	
	
	public MenPai(String name, long time, CyclicBarrier cyclicBarrier) {
		super() ;
		this.name = name ;
		this.time = time ;
		this.cyclicBarrier = cyclicBarrier ;
	}



	@Override
	public void run() {
		System.out.println(name + "出发前往光明顶") ;
		try {
			Thread.sleep(time);
			System.out.println(name + "到达光明顶") ;
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
}


/**
 * CyclicBarrier使用demo
 * 通过CyclicBarrier模仿六大门派围攻光明顶
 *
 */
public class CyclicBarrierDemo {
	
	public static void main(String [] args) {
		// 定义CyclicBarrier,传入6表示需要六大门派到期
		CyclicBarrier cyclicBarrier = new CyclicBarrier(6, new Runnable() {
			@Override
			public void run() {
				System.out.println("六大门派到期,开始围攻光明顶") ;
			}
		});
		new Thread(new MenPai("少林", 3000, cyclicBarrier)).start();
		new Thread(new MenPai("武当", 5000, cyclicBarrier)).start();
		new Thread(new MenPai("峨眉", 2000, cyclicBarrier)).start();
		new Thread(new MenPai("昆仑", 6000, cyclicBarrier)).start();
		new Thread(new MenPai("崆峒", 3000, cyclicBarrier)).start();
		new Thread(new MenPai("华山", 3500, cyclicBarrier)).start();
		
	}
	
	
	
}
