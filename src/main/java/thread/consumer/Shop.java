package thread.consumer;

import java.util.concurrent.locks.Condition ;
import java.util.concurrent.locks.Lock ;
import java.util.concurrent.locks.ReentrantLock ;

/**
 * 消费者
 * @author Administrator
 *
 */
class Consumer implements Runnable{
	private Shop shop;
	
	public Consumer(Shop shop) {
		this.shop = shop ;
	}
	@Override
	public void run() {
		while (true) {
			shop.consume();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}

/**
 * 生产者
 * @author Administrator
 *
 */
class Producer implements Runnable{
	private Shop shop;
	
	public Producer(Shop shop) {
		this.shop = shop ;
	}
	@Override
	public void run() {
		while (true) {
			shop.produce();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}


public class Shop {
	private int count;
	private final int MAX_COUNT = 10;
	Lock lock = new ReentrantLock();
	// 生产者
	Condition produceCondition = lock.newCondition();
	// 消费者
	Condition consumeCondition = lock.newCondition();
	
	public void produce(){
		lock.lock();
		while (count >= MAX_COUNT) {
			try {
				System.out.println("库存已满,生产者" + Thread.currentThread().getName() + ":等待") ;
				produceCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count++;
		System.out.println(Thread.currentThread().getName() + "生产了,库存为:" + count) ;
		consumeCondition.signal();
		lock.unlock();
	}
	
	public void consume(){
		lock.lock();
		while (count <= 0) {
			try {
				System.out.println("库存已空,消费者" + Thread.currentThread().getName() + ":等待") ;
				produceCondition.signal();
				consumeCondition.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		count--;
		System.out.println(Thread.currentThread().getName() + "消费了,库存为:" + count) ;
		produceCondition.signal();
		lock.unlock();
	}
	
	public static void main(String [] args) {
		Shop shop = new Shop();
		Producer producer = new Producer(shop);

		Consumer consumer = new Consumer(shop);
		new Thread(producer).start();
		new Thread(producer).start();
		new Thread(consumer).start();
		new Thread(consumer).start();
		new Thread(consumer).start();
	}
	
}
