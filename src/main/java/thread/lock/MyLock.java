package thread.lock;

import java.util.concurrent.TimeUnit ;
import java.util.concurrent.locks.Condition ;
import java.util.concurrent.locks.Lock ;
/**
 * 实现Lock接口实现自己的可重入锁
 * 这里只实现lock和unlock方法
 * @author Administrator
 *
 */
public class MyLock implements Lock {
	// 锁标识
	private boolean isLocked = false;
	// 标注当前锁被哪个线程所持有
	private Thread lockBy = null;
	// 记录当前线程持有锁的数量
	private int lockCount = 0;
	
	@Override
	public synchronized void lock() {
		Thread currentThread = Thread.currentThread();
		if (isLocked && lockBy != currentThread) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 获取到锁时,将状态更新为被锁定状态
		isLocked = true;
		// 锁计数器加1
		lockCount++;
		// 将
		lockBy = currentThread;
	}
	
	@Override
	public synchronized void unlock() {
		Thread currentThread = Thread.currentThread();
		// 只有是持有这个锁的线程调用unlock方法时才需要处理
		if (lockBy == currentThread) {
			lockCount--;
			// 锁计数器为0时,才能将isLocked状态置位非锁定状态
			if (lockCount == 0) {
				isLocked = false;
				lockBy = null;
				notify();
			}
		}
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean tryLock() {
		// TODO Auto-generated method stub
		return false ;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		// TODO Auto-generated method stub
		return false ;
	}


	@Override
	public Condition newCondition() {
		// TODO Auto-generated method stub
		return null ;
	}
	
}
