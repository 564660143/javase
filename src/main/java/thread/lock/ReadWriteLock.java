package thread.lock;

import java.util.HashMap ;
import java.util.Map ;
import java.util.concurrent.Executors ;
import java.util.concurrent.ThreadPoolExecutor ;
import java.util.concurrent.locks.Lock ;
import java.util.concurrent.locks.ReentrantReadWriteLock ;

/**
 * 读写锁
 *
 */
public class ReadWriteLock {
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock = readWriteLock.readLock();
	private Lock writeLock = readWriteLock.writeLock();
	private Map<String, String> map = new HashMap<>();
	
	public void put(String key, String value){
		writeLock.lock();
		System.out.println(Thread.currentThread().getName() + "写操作正在执行---") ;
		map.put(key, value);
		writeLock.unlock();
		System.out.println(Thread.currentThread().getName() + "写操作执行结束---") ;
	}
	
	public String get(String key){
		readLock.lock();
		System.out.println(Thread.currentThread().getName() + "读操作正在执行---") ;
		try {
			Thread.sleep(2000); 
			return map.get(key);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return null;
		} finally {
			readLock.unlock();
			System.out.println(Thread.currentThread().getName() + "读操作执行结束---") ;
		}
	}
	
	
	public static void main(String [] args) {
		ReadWriteLock readWriteLock = new ReadWriteLock();
		readWriteLock.put("key1", "value1");
		readWriteLock.put("key2", "value3");
		readWriteLock.put("key3", "value3");
		new Thread(new Runnable() {
			@Override
			public void run() {
//				readWriteLock.put("key4", "value4");
				readWriteLock.get("key1");
			}
		}).start();
		
		new Thread(new Runnable() {
			@Override
			public void run() {
//				readWriteLock.put("key5", "value5");
				readWriteLock.get("key2");
			}
		}).start();
		
	}
	
}
