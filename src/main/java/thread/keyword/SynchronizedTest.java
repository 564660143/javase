package thread.keyword;

class Seller extends Thread{
  private String name;
  private TicketPool pool;
  
  public Seller(String name, TicketPool pool) {
    this.name = name;
    this.pool = pool;
  }

  public void run(){
    while(true){
      // 同步代码块方式
//      int tiketNo = pool.getTikets();
      // 同步方法
      int tiketNo = pool.getTiketsSync();
      // 同步静态方法，同步静态方法时以当前对象的class对象作为同步标志
//      int tiketNo = TicketPool.getTiketsStatic();


      if (tiketNo == 0) {
        return;
      }
      System.out.println(name + " : " + tiketNo);
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }
    
  }
  
}

class TicketPool{
  public static int tikets = 100;
  /**
   * 同步代码块
   * @return
   */
  public int getTiketsSync(){
    int temp;
    synchronized (this) {
      temp = tikets;
      tikets = tikets -1;
    }
    return temp>0 ? temp : 0;
  }
  
  /**
   * 同步方法
   * @return
   */
  public synchronized int getTikets(){
    int temp = tikets;
    tikets = tikets -1;
    return temp>0 ? temp : 0;
  }
  
  /**
   * 同步静态方法
   * @return
   */
  public synchronized static int getTiketsStatic(){
    int temp = tikets;
    tikets = tikets -1;
    return temp>0 ? temp : 0;
  }
  
  
}



public class SynchronizedTest {

  public static void main(String[] args) {
    TicketPool pool = new TicketPool();
    Seller s1 = new Seller("s-1", pool);
    Seller s2 = new Seller("s-2", pool);
    s1.start();
    s2.start();
  }

}
