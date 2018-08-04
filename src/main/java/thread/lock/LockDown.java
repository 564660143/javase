package thread.lock;

import java.util.concurrent.locks.Lock ;
import java.util.concurrent.locks.ReentrantReadWriteLock ;

/**
 * 锁降级
 * @author Administrator
 *
 */
public class LockDown {
	private ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	Lock readLock = readWriteLock.readLock();
	Lock writeLock = readWriteLock.writeLock();
	
	public void testLockDown() {
		writeLock.lock();
		System.out.println("获取到一个写锁") ;
		readLock.lock();
		System.out.println("写锁还未释放时获取一个读锁") ;
		writeLock.unlock();
		System.out.println("释放写锁") ;
		readLock.unlock();
		System.out.println("释放读锁") ;
	}
	
	
	public static void main(String [] args) {
		LockDown lockDown = new LockDown();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				lockDown.testLockDown();				
			}
		}).start();
	}
	
}
