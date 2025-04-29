package Kingdom;

import Castle.TreasureRoomDoor;
import Gems.Gem;
import Logger.Catalogue;
import MyArrayList.MyArrayList;

import java.util.ArrayList;
import java.util.List;

public class GemTransporter implements Runnable
{
  private final GemDeposit gemDeposit;
  private final TreasureRoomDoor treasureRoomDoor;
  private final List<Gem> container;
  private int targetWorth;
  private int currentWorth;
  private final int minTargetWorth = 50;
  private final int maxTargetWorth = 200;
  private final int sleepTime = 1000; // 1 second
  private final Catalogue catalogue;
  private String name = new String(); //i guess for loggin in acces method


  public GemTransporter(GemDeposit gemDeposit,
      TreasureRoomDoor treasureRoomDoor)
  {
    this.gemDeposit = gemDeposit;
    this.treasureRoomDoor = treasureRoomDoor;
    this.container = new ArrayList<>();
    this.targetWorth = generateRandomTarget();
    this.currentWorth = 0;
    this.catalogue = Catalogue.getInstance();
    this.name = name;
  }

  private int generateRandomTarget()
  {
    return (int) (Math.random() * (maxTargetWorth - minTargetWorth + 1))
        + minTargetWorth;
  }
  private int generateTargetWorth()
  {
    return (int) (Math.random() * (maxTargetWorth - minTargetWorth + 1))
        + minTargetWorth;
  }

  /*
    the behaviour of the GemTransporter must be strictly as follows:`
      1. Generate a random target number e.g., between 50 and 200 (this is the minimum worth of Gems the GemTransporter will transport).
      2. Then, continuously get the next Gem from the GemDeposit.
      3. Continue to do so until the total worth of all Gems is equal to or more than the original target number.
      4. Then, for now, just clear the container (currently we have no place to put them, this will come later!)
      5. Sleep for a little while
      6. Go back to step 1
In relevant places you should have the Logger.Catalogue print out what is happening so you can follow along.

in step 2 update step 4 to reference the TreasureRoomDoor and use it to insert gems one at a time into the TreasureRoom

acts as a writer: remember to acquire write access, execute then release writer access to the TreasureRoomDoor again
Make sure the List in GemTransporter is cleared when don
   */

  //here a lil confused, need to think
  @Override public void run()
  {
    while (true)
    {
     catalogue.log(name + ": New target to collect= " + targetWorth);

     try{
       while (currentWorth < targetWorth) {
         Gem gem = gemDeposit.take();
         container.add(gem);
         currentWorth += gem.getValue();
         catalogue.log(name + ": Picked up " + gem.getName() + " worth " + gem.getValue() + ". Current total = " + currentWorth);
       }
        catalogue.log(name + ": Target reached, total worth = " + currentWorth);

       treasureRoomDoor.acquireWriteAccess(name);
       catalogue.log(name + ": Transporting gems to TreasureRoom.....");

       for (Gem gem : container) {
         treasureRoomDoor.addValuable(gem);
         catalogue.log(name + ": Deposited " + gem.getName() + " worth" + gem.getValue());
       }
       treasureRoomDoor.releaseWriteAccess(name);
     }catch (InterruptedException e) {
       Thread.currentThread().interrupt();
       catalogue.log(name + ": Interrupted while transporting gems. Exiting...");
       break;
     }catch (Exception e) {
       catalogue.log(name + ": Error while transporting gems: " + e.getMessage());
     }

     container.clear();
     currentWorth = 0;
     targetWorth = generateTargetWorth();
      catalogue.log(name + ": Transporting done. Sleeping....");

      try {
        Thread.sleep(sleepTime);
      }catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        catalogue.log(name + ": Interrupted while sleeping. Exiting...");
        break;
      }
    }
  }


}
