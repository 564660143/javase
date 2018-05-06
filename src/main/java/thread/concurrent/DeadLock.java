package thread.concurrent;

class DeadThread extends Thread {
  private String str1;
  private String str2;

  public DeadThread(String str1, String str2) {
    this.str1 = str1;
    this.str2 = str2;
  }

  @Override
  public void run() {
    synchronized (str1) {
      System.out.println("��ö���" + str1 + "����");
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      synchronized (str2) {
        System.out.println("��ö���" + str2 + "����");
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }

    }
  }

}


public class DeadLock {

  public static void main(String[] args) {
    String str1 = "aaaaa";
    String str2 = "bbbbb";
    new DeadThread(str1, str2).start();
    new DeadThread(str2, str1).start();

  }

}
