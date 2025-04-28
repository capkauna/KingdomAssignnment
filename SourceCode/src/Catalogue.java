import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Catalogue
{
  private static Catalogue instance;
  private static Lock lock = new ReentrantLock();

  private Catalogue() {

  }

  public static synchronized Catalogue getInstance() {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null)
         {instance = new Catalogue();}
      }
    }
    return instance;
  }

  //template
  public synchronized void log(String message) {
    System.out.println("[Catalogue] " + message);
  }



  //instances for:
  // when and how much GemTransporter add to TreasureRoom;
  //when King holds party or cancels it;
  //when Accountants calculated the total wealth in TreasureRoom
  //or include logs for when one or more of them are waiting to enter TreasureRoom
}
