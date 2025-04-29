package Castle;
import Kingdom.*;
import Gems.Gem;
import Logger.Catalogue;

import java.util.List;
public class Accountant implements Runnable
{
  /*
  This class wants to count the total worth of all Gems in the TreasureRoom The Accountant acts as a reader class.

  It must be a Runnable class so it can be run in a separate thread.

  The accountant will have a while(true) loop in the run() method, and will follow these steps strictly:

  Acquire read access
  Count the total worth of all Gems in the TreasureRoom (use sleep to simulate it takes time to count the Gems)
  Print out the total worth (using the Logger.Catalogue class)
  Release read access
  Sleep for a little while

Include relevant printouts using the Logger.Catalogue class.
   */

  private TreasureRoomDoor treasureRoomDoor;
  private String name;

  public Accountant(TreasureRoomDoor treasureRoomDoor, String name)
  {
    this.treasureRoomDoor = treasureRoomDoor;
    this.name = name;
  }

  @Override public void run()
  {
    while (true){
      try{
        treasureRoomDoor.acquireReadAccess(name);

        List<Gem> gems = treasureRoomDoor.lookAtAllGems();
        int totalValue = 0;
        for (Gem gem : gems) {
          totalValue += gem.getValue();
        }

        Catalogue.getInstance().log(" . . . Counting . . .");
        Thread.sleep(2000);
        Catalogue.getInstance().log(name + " counted total worth: " + totalValue);
        treasureRoomDoor.releaseReadAccess(name);
        Catalogue.getInstance().log(" . . . Nap time . . . before counting again . . .");
        Thread.sleep(3000); // Sleep for a little while before counting again

      }catch (InterruptedException e){
        throw new RuntimeException(e);
      }
    }

  }

}
