package thread.concurrentutil;

import java.util.Random ;
import java.util.concurrent.CyclicBarrier ;

/**
 * 玩家类,表示打麻将的人
 */
class Player implements Runnable {
	// 姓名
	private String name;
	// 到达麻将馆所需时间
	private long time;
	// 到达麻将馆所需时间
	CyclicBarrier cyclicBarrier;
	
	public Player(String name, long time, CyclicBarrier cyclicBarrier) {
		super() ;
		this.name = name ;
		this.time = time ;
		this.cyclicBarrier = cyclicBarrier ;
	}


	@Override
	public void run() {
		System.out.println(name + "出发前往麻将馆") ;
		try {
			// 休眠一段时间,表示路上所需时间
			Thread.sleep(time);
			// 需要等待集齐四个人
			cyclicBarrier.await();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}


/**
 * CyclicBarrier使用demo
 * 通过CyclicBarrier模仿打麻将的场景,每到期四个人就开一桌麻将
 *
 */
public class CyclicBarrierDemo2 {
	
	public static void main(String [] args) throws Exception {
		// 定义CyclicBarrier,传入5表示加上当前main线程在内,需要有5个线程处于等待状态时,才会唤醒所有线程
		CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
		// 人员编号, 桌子编号
		int playerIndex = 1, deskIndex = 1;
		while(true){
			new Thread(new Player("玩家" + playerIndex++, new Random().nextInt(5000), cyclicBarrier)).start();
			new Thread(new Player("玩家" + playerIndex++, new Random().nextInt(5000), cyclicBarrier)).start();
			new Thread(new Player("玩家" + playerIndex++, new Random().nextInt(5000), cyclicBarrier)).start();
			new Thread(new Player("玩家" + playerIndex++, new Random().nextInt(5000), cyclicBarrier)).start();
			Thread.currentThread().interrupt();
			// 在人员到期之前,让主线程处于等待状态
			cyclicBarrier.await();
			System.out.println("人员已到齐,第" +  (deskIndex++) + "桌开始游戏") ;
		}
		
	}
	
}
