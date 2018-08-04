package thread.keyword;

class MenPai extends Thread{
  private String name;
  private long time;
  public MenPai(String name, long time){
    this.name = name;
    this.time = time;
  }
  
  
  public void run(){
    System.out.println(name + "出发了");
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + "到了，耗时：" + time);
  }
  
}


/**
 * join关键字，在当前线程执行其他线程的join方法时，表示将其他线程加入当前线程
 * 当前线程需要等待其他线程执行完成才能继续执行
 * @author Administrator
 *
 */
public class JoinDemo {
  
  

  public static void main(String[] args) {
    MenPai m1 = new MenPai("少林", 1000);
    MenPai m2 = new MenPai("武当", 2000);
    MenPai m3 = new MenPai("峨眉", 3000);
    MenPai m4 = new MenPai("昆仑", 4000);
    MenPai m5 = new MenPai("崆峒", 5000);
    MenPai m6 = new MenPai("华山", 6000);
    m1.start();
    m2.start();
    m3.start();
    m4.start();
    m5.start();
    m6.start();
    
    // join(),加入当前的main线程
    try {
      m1.join();
      m2.join();
      m3.join();
      m4.join();
      m5.join();
      m6.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    
    // m1-m6通过join()方法加入main线程，main线程需要等待这六个线程执行完毕才能继续执行
    System.out.println("六大门派围攻光明顶");
    
    
  }

}
