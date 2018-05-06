package thread.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * 公共资源类
 * 
 * @author Administrator
 *
 */
class Resource {
  public static int max = 100;
  private List<Integer> list = new ArrayList<>();

  
  /******************************同步方法**************************************/
  public synchronized void add(Integer in) {
      while (list.size() >= max) {
        try {
          System.out.println("生产者wait-> list size:" + list.size());
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      list.add(in);
      this.notifyAll();
  }
  
  public synchronized void remove() {
      while (list.size() == 0) {
        try {
          System.out.println("消费者wait -> list size:" + list.size());
          this.wait();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      int i = list.remove(0);
      System.out.println("消费了：" + i);
      this.notifyAll();
   }
  /******************************同步方法**************************************/

  
  
  /******************************同步代码块**************************************/
//  public  void add(Integer in) {
//    synchronized(this){
//      while (list.size() >= max) {
//        try {
//          System.out.println("生产者wait-> list size:" + list.size());
//          this.wait();
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }
//      list.add(in);
//      this.notifyAll();
//    }
//  }

//  public  void remove() {
//    synchronized(this){
//      while (list.size() == 0) {
//        try {
//          System.out.println("消费者wait -> list size:" + list.size());
//          this.wait();
//        } catch (InterruptedException e) {
//          e.printStackTrace();
//        }
//      }
//      int i = list.remove(0);
//      System.out.println("消费了：" + i);
//      this.notifyAll();
//    }
//  }

  /******************************同步代码块**************************************/

  
  
}


class Consumer extends Thread {
  private String name;
  private Resource resource;

  public Consumer(String name, Resource resource) {
    this.name = name;
    this.resource = resource;
  }

  @Override
  public void run() {
    while (true) {
      resource.remove();
      try {
        Thread.sleep(50);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}


class Producer extends Thread {
  private String name;
  private Resource resource;
  private static int i = 0;

  public Producer(String name, Resource resource) {
    this.name = name;
    this.resource = resource;
  }

  @Override
  public void run() {
    while (true) {
      resource.add(new Integer(i++));
      try {
        Thread.sleep(60);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}



public class ProducerAndConsumer {

  public static void main(String[] args) {
    Resource resource = new Resource();
    Producer p1 = new Producer("p1", resource);
    Consumer c1 = new Consumer("c1", resource);
    Consumer c2 = new Consumer("c2", resource);
    p1.start();
    c1.start();
    c2.start();
    
  }

}
