package thread.lock ;

import java.util.concurrent.TimeUnit ;
import java.util.concurrent.locks.AbstractQueuedSynchronizer ;
import java.util.concurrent.locks.Condition ;
import java.util.concurrent.locks.Lock ;

/**
 * 使用AQS实现一个可重入锁
 * @author Administrator
 *
 */
public class MyLockByAQS implements Lock {
	
	private Helper helper = new Helper() ;
	
	@Override
	public void lock() {
		helper.acquire(1) ;
	}
	
	@Override
	public void lockInterruptibly() throws InterruptedException {
		helper.acquireInterruptibly(1) ;
	}
	
	@Override
	public boolean tryLock() {
		return helper.tryAcquire(1) ;
	}
	
	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return helper.tryAcquireSharedNanos(1, unit.toNanos(time)) ;
	}
	
	@Override
	public void unlock() {
		helper.release(1) ;
	}
	
	@Override
	public Condition newCondition() {
		return helper.newCondition() ;
	}
	
	private class Helper extends AbstractQueuedSynchronizer {
		
		@Override
		protected boolean tryAcquire(int arg) {
			Thread current = Thread.currentThread() ;
			// 线程第一次进入时可以获取到锁
			int state = getState() ;
			if (state == 0) {
				if (compareAndSetState(0, arg)) {
					// 获取到锁之后,设置当前拥有独占访问的线程为当前线程
					setExclusiveOwnerThread(current) ;
					return true ;
				}
				// 这个判断保证锁可重入
			} else if (current == getExclusiveOwnerThread()) {
				// 当前线程多次进入时,state增加
				setState(state + arg) ;
				return true ;
			}
			
			// 非当前线程第二次进入时无法获取到锁
			return false ;
		}
		
		@Override
		protected boolean tryRelease(int arg) {
			Thread current = Thread.currentThread() ;
			boolean flag = false ;
			if (current == getExclusiveOwnerThread()) {
				int state = getState() - arg ;
				
				if (state == 0) {
					// 设置当前拥有独占访问的线程为空,表示锁释放
					setExclusiveOwnerThread(null) ;
					flag = true ;
				}
				
				// 更新state
				setState(state) ;
			}
			
			return flag ;
		}
		
		public Condition newCondition() {
			return new ConditionObject() ;
		}
		
	}
	
}
