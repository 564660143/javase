package thread.keyword;

 class MyThread extends Thread{
   private String threadName;
   
   public MyThread(String threadName){
     this.threadName = threadName;
   }
   
  @Override
  public void run() {
    while(true){
      System.out.println(threadName + " say hello world");
      // yield会让出CPU，让出之后再去抢占CPU
      Thread.yield();
    }
  }
   
   
 }



/**
 * yield关键字使用，让出CPU，是个瞬时状态，让出CPU之后会再次去抢占CPU
 * @author Administrator
 *
 */
public class YieldDemo {
  
  public static void main(String[] args) {
    MyThread t1 = new MyThread("Thread 1");
    MyThread t2 = new MyThread("Thread 2");
    t1.start();
    t2.start();
  }

}
