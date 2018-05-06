package thread.keyword;

class MenPai extends Thread{
  private String name;
  private long time;
  public MenPai(String name, long time){
    this.name = name;
    this.time = time;
  }
  
  
  public void run(){
    System.out.println(name + "������");
    try {
      Thread.sleep(time);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println(name + "���ˣ���ʱ��" + time);
  }
  
}


/**
 * join�ؼ��֣��ڵ�ǰ�߳�ִ�������̵߳�join����ʱ����ʾ�������̼߳��뵱ǰ�߳�
 * ��ǰ�߳���Ҫ�ȴ������߳�ִ����ɲ��ܼ���ִ��
 * @author Administrator
 *
 */
public class JoinDemo {
  
  

  public static void main(String[] args) {
    MenPai m1 = new MenPai("����", 1000);
    MenPai m2 = new MenPai("�䵱", 2000);
    MenPai m3 = new MenPai("��ü", 3000);
    MenPai m4 = new MenPai("����", 4000);
    MenPai m5 = new MenPai("���", 5000);
    MenPai m6 = new MenPai("��ɽ", 6000);
    m1.start();
    m2.start();
    m3.start();
    m4.start();
    m5.start();
    m6.start();
    
    // join(),���뵱ǰ��main�߳�
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
    
    // m1-m6ͨ��join()��������main�̣߳�main�߳���Ҫ�ȴ��������߳�ִ����ϲ��ܼ���ִ��
    System.out.println("��������Χ��������");
    
    
  }

}
