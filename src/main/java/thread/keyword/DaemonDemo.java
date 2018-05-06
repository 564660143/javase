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
    System.out.println(no + "�����Ŀ��˿�ʼ������");
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(no + "�����Ŀ��˽������Ѻ�ʱ��" + time);
    
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
 * Daemon,��һ���߳�����Ϊ�ػ��̣߳���һ�����������з��ػ��̶߳�����ʱ���������Ҳ������
 * @author Administrator
 *
 */
public class DaemonDemo {

  public static void main(String[] args) {
    Consumer c1 = new Consumer(1, 3000);
    Consumer c2 = new Consumer(2, 6000);
    Consumer c3 = new Consumer(3, 9000);
    Waiter w1 = new Waiter();
    
    // �˴���ʾ��w1�߳�����Ϊ�ػ��̣߳�w1�߳̽���c1��c2��c3�����߳�ִ�����֮�����ִ�У�
    // setDaemon�������߳�����֮ǰ����
    w1.setDaemon(true);
    synchronized (args) {
      
    }
    w1.start();
    c1.start();
    c2.start();
    c3.start();
  }

}
