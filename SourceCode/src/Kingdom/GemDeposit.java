package Kingdom;

import Gems.Gem;
import Logger.Catalogue;
import MyArrayList.MyArrayList;

public class GemDeposit implements BlockingQueue<Gem>
{

  /*
  used by the GemMineWorker (producer) and GemTransporter (consumer)
  must use MyArrayList for the implementation of a blocking queue
  should use the Adapter pattern to convert the MyArrayList into something that acts like a BlockingQueue
   */
private final MyArrayList<Gem> queue;
private final int capacity;
private final Object lock = new Object();
private final Catalogue catalogue;

public GemDeposit(int capacity){
  if (capacity <= 0) {
    throw new IllegalArgumentException("Capacity must be positive");
  }
  this.queue = new MyArrayList<Gem>(capacity);
  this.capacity = capacity;
  this.catalogue = Catalogue.getInstance();
}

public void put(Gem gem) throws InterruptedException{
  synchronized (lock) {
    while (queue.size() >= capacity) {
      catalogue.log("Deposit full. Waiting to be emptied... ");
      System.out.println("Deposit full. Waiting to be emptied... ");
      lock.wait();
    }

    queue.add(gem);
    catalogue.log("Added " + gem.getName() + ". " + size() + " gems in deposit.");
    System.out.println("Added " + gem.getName() + ".");
    lock.notifyAll();
  }
}

public Gem take() throws InterruptedException{
  synchronized (lock) {
    while (isEmpty()){
      catalogue.log("Deposit empty. Cart waiting... ");
      System.out.println("Deposit empty. Cart waiting... ");
      lock.wait();
    }
    Gem gem = queue.remove(0);
    catalogue.log("Taking " + gem.getName() + " from deposit. ");
    System.out.println("Taking " + gem.getName() + " from deposit. ");
    lock.notifyAll();
    return gem;
  }
}
public int size(){
  synchronized (lock){
    return queue.size();
  }
}
public boolean isEmpty(){
  synchronized ( lock) {
    return queue.isEmpty();
  }
}

}
