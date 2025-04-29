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

public GemDeposit(int capacity){
  if (capacity <= 0) {
    throw new IllegalArgumentException("Capacity must be positive");
  }
  this.queue = new MyArrayList<Gem>(capacity);
  this.capacity = capacity;
}

public void put(Gem gem) throws InterruptedException{
  synchronized (lock) {
    while (queue.size() >= capacity) {
      Catalogue.getInstance().log("Queue full. Producer waiting... ");
      lock.wait();
    }

    queue.add(gem);
    Catalogue.getInstance().log("Added gem: " + gem.getValue());
    lock.notifyAll();
  }
}

public Gem take() throws InterruptedException{
  synchronized (lock) {
    while (queue.isEmpty()){
      Catalogue.getInstance().log("Queue empty. Consumer waiting... ");
      lock.wait();
    }
    Gem gem = queue.remove(0);
    Catalogue.getInstance().log("Taking gem: " + gem.getValue());
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
