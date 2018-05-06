package thread.keyword;

import java.util.Date;

class Consumer extends Thread{
  private int no;
  private int time;
  
  public Consumer(int no, int time) {
    this.no = no;
    this.time = time;
  }


  public void run(){
    System.out.println(no + "号桌的客人开始消费了");
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(no + "号桌的客人结束消费耗时：" + time);
    
  }
  
  
}


class Waiter extends Thread{
  
  public void run(){
    try {
      while(true){
        System.out.println(new Date());
        Thread.sleep(1000);
      }
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }
  
}


/**
 * Daemon,将一个线程设置为守护线程，当一个进程中所有非守护线程都结束时，这个进程也将结束
 * @author Administrator
 *
 */
public class DaemonDemo {

  public static void main(String[] args) {
    Consumer c1 = new Consumer(1, 3000);
    Consumer c2 = new Consumer(2, 6000);
    Consumer c3 = new Consumer(3, 9000);
    Waiter w1 = new Waiter();
    
    // 此处表示将w1线程设置为守护线程，w1线程将在c1、c2、c3三个线程执行完成之后结束执行，
    // setDaemon必须在线程启动之前设置
    w1.setDaemon(true);
    synchronized (args) {
      
    }
    w1.start();
    c1.start();
    c2.start();
    c3.start();
  }

}
