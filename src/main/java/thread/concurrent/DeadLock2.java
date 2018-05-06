package thread.concurrent;



public class DeadLock2 {

  public static void main(String[] args) {
    String str1 = "aaaaa";
    String str2 = "bbbbb";
    new DeadThread(str1, str2).start();
    new DeadThread(str2, str1).start();

  }

}
