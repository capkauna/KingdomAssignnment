package Kingdom;

import Castle.TreasureRoomDoor;
import Gems.Gem;
import Logger.Catalogue;

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


  public GemTransporter(String name, GemDeposit gemDeposit,
      TreasureRoomDoor treasureRoomDoor)
  {
    this.gemDeposit = gemDeposit;
    this.treasureRoomDoor = treasureRoomDoor;
    this.container = new ArrayList<>();
    this.targetWorth = generateRandomTargetWorth();
    this.currentWorth = 0;
    this.catalogue = Catalogue.getInstance();
    this.name = name;
  }

  private int generateRandomTargetWorth()
  {
    return (int) (Math.random() * (maxTargetWorth - minTargetWorth + 1))
        + minTargetWorth;
  }
//  private int generateTargetWorth() //does the same as above
//  {
//    return (int) (Math.random() * (maxTargetWorth - minTargetWorth + 1))
//        + minTargetWorth;
//  }

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



  @Override public void run()
  {
    while (true)
    {
      catalogue.log(name + " starts with a collection target of " + targetWorth);

      try {
        while (currentWorth < targetWorth) {
          Gem gem = gemDeposit.take();
          container.add(gem);
          currentWorth += gem.getValue();
          catalogue.log(name + ": Picked up " + gem.getName() + " [worth " + gem.getValue() + "]. Current total = " + currentWorth);
          System.out.println(name + ": Picked up " + gem.getName() + " [worth " + gem.getValue() + "]. Current total = " + currentWorth);
        }
        catalogue.log(name + " reached collection target, total worth = " + currentWorth);
        System.out.println(name + "  reached collection target, total worth = " + currentWorth);

        treasureRoomDoor.acquireWriteAccess(name);
        try {
          catalogue.log(name + " is transporting gems to TreasureRoom...");
          System.out.println(name + " is transporting gems to the Treasure Room...");

          for (Gem gem : container) {
            treasureRoomDoor.addValuable(gem);
            catalogue.log(name + " deposited " + gem.getName() + " [worth " + gem.getValue() + "]");
            System.out.println(name + " deposited " + gem.getName() + " [worth " + gem.getValue() + "]");
          }
        } finally {
          treasureRoomDoor.releaseWriteAccess(name);
        }
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        catalogue.log(name + " was interrupted while transporting gems. Exiting...");
        System.out.println(name + " was interrupted while transporting gems. Exiting...");
        break;
      } catch (Exception e) {
        catalogue.log(name + " toppled off the tracks because " + e.getMessage());
        System.out.println(name + " toppled off the tracks because " + e.getMessage());
      } finally {
        container.clear();
        currentWorth = 0;
        targetWorth = generateRandomTargetWorth();
        catalogue.log(name + ": Transporting done. New target worth for next transport set to " + targetWorth + ". Sleeping for now...");
        System.out.println(name + ": Transporting done. New target worth for next transport set to " + targetWorth + ". Sleeping for now...");
      }

      try {
        Thread.sleep(sleepTime);
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        catalogue.log(name + " was interrupted from sleeping. Exiting...");
        System.out.println(name + " was interrupted from sleeping. Exiting...");
        break;
      }
    }
  }


}
