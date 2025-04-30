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
    System.out.println(actor + " was allowed in the treasure room.");
  }

  public synchronized void releaseReadAccess(String actor)
  {
    readers--;
    treasureRoomDoor.releaseReadAccess(actor);
    if(readers==0){
      notifyAll();
      System.out.println(actor + " left the treasure room.");
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
    System.out.println(actor + " was allowed to handle the treasure room.");
  }

  public synchronized void releaseWriteAccess(String actor)
  {
    writerActive = false;
    treasureRoomDoor.releaseWriteAccess(actor);
    notifyAll();
    System.out.println(actor + " finished their work and left the treasure room.");
  }

  @Override
  public Gem retrieveValuable()
  {
    if (writerActive || readers > 0)
      {
        throw new IllegalStateException("Cannot retrieve valuable while the room is being used.");
      }

    Gem retrievedGem = treasureRoomDoor.retrieveValuable();

    if (retrievedGem == null)
      {
        System.out.println("The treasure room has no more gems.");
        return null;
      }
    System.out.println(retrievedGem.getName() + " retrieved from the treasure room.");
    return retrievedGem;

  }

  @Override
  public void addValuable(Gem v)
  {
    if (writerActive || readers > 0)
      {
        throw new IllegalStateException("Can\'t add things while the room is being used.");
      }
    treasureRoomDoor.addValuable(v);
    System.out.println(v.getName() + " added to the treasure room.");

  }

  @Override
  public List<Gem> lookAtAllGems()
  {
    if (writerActive || readers > 0)
      {
        throw new IllegalStateException("Cannot look at gems while the room is being used.");
      }
    System.out.println("Admiring the gems in the treasure room.");
    return treasureRoomDoor.lookAtAllGems();

  }


}
