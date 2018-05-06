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
      // yield���ó�CPU���ó�֮����ȥ��ռCPU
      Thread.yield();
    }
  }
   
   
 }



/**
 * yield�ؼ���ʹ�ã��ó�CPU���Ǹ�˲ʱ״̬���ó�CPU֮����ٴ�ȥ��ռCPU
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
