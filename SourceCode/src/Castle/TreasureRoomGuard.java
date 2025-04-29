package Castle;

import Gems.Gem;
import java.util.ArrayList;
import java.util.List;

public class TreasureRoomGuard implements TreasureRoomDoor
{
  /*
  use the Proxy Pattern
  also implement a solution to the Readers-Writers problem here since he already controls access to the treasure room
  can use any approach to solve the Readers-Writers problem
   */

  private TreasureRoomDoor treasureRoomDoor;
  private int readers;
  private boolean writerActive;

  public TreasureRoomGuard(TreasureRoomDoor treasureRoomDoor)
  {
    this.treasureRoomDoor = treasureRoomDoor;
    this.readers = 0;
    this.writerActive = false;
  }

  public synchronized void acquireReadAccess(String actor)
  {
    while(writerActive){
      try{
        wait();
      }catch (InterruptedException e){
        throw new RuntimeException(e);
      }
    }

    readers++;
    treasureRoomDoor.acquireReadAccess(actor);
  }

  public synchronized void releaseReadAccess(String actor)
  {
    readers--;
    treasureRoomDoor.releaseReadAccess(actor);
    if(readers==0){
      notifyAll();
    }
  }

  public synchronized void acquireWriteAccess(String actor)
  {
    // If there are any active writers or readers, wait
    if(writerActive || readers > 0){
      try{
        wait();
      }catch (InterruptedException e){
        throw new RuntimeException(e);
      }
    }
    writerActive = true;
    treasureRoomDoor.acquireWriteAccess(actor);
  }

  public synchronized void releaseWriteAccess(String actor)
  {
    writerActive = false;
    treasureRoomDoor.releaseWriteAccess(actor);
    notifyAll();
  }

  @Override
  public Gem retrieveValuable()
  {
    return treasureRoomDoor.retrieveValuable();
  }

  @Override
  public void addValuable(Gem v)
  {
    treasureRoomDoor.addValuable(v);
  }

  @Override
  public List<Gem> lookAtAllGems()
  {
    return treasureRoomDoor.lookAtAllGems();
  }


}
