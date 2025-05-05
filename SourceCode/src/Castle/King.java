package Castle;
import Gems.Gem;
import Logger.Catalogue;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class King implements Runnable
{
  /*
  also acts as a writer.

  The King wants to take out Gems from the TreasureRoom in order to throw a party.

  The behaviour of the King must follow these steps strictly:

  1. The King will determine the cost of the party - a random number (could be between 50-150)
  2. Then he will acquire write access
  3. He starts retrieving the necessary Gems, one at a time and inspects their value, adding the total value so far together
  4. If the cost cannot be met after retrieving all Gems, he will cancel the party and put the Gems back (this should include a short sleep to simulate it takes time to get the desired Gems)
  5. After inspecting all Gems he releases write access
  6. If the target is met, he will hold a party. Spend the Gems retrieved (remove from the list).
  7. After a party, he needs to sleep! Sleep for a while
  8. Start over from step 1

Include relevant printouts using the Logger.Catalogue class.
   */

  private TreasureRoomDoor treasureRoomDoor;
  private String name;
  private Random random;
  private Catalogue catalogue = Catalogue.getInstance();

  public King(TreasureRoomDoor treasureRoomDoor, String name)
  {
    this.treasureRoomDoor = treasureRoomDoor;
    this.name = name;
    this.random = new Random();
  }

  @Override
  public void run() {
    while (true) {
      try {
        int partyCost = random.nextInt(101) + 50; // between 50 and 150
        catalogue.log("King " + name + " is planning a party! He needs " + partyCost + " worth of gems.");

        treasureRoomDoor.acquireWriteAccess(name);
        try {
          Gem gem;
          int totalGemsCollected = 0;
          List<Gem> gatheredGems = new ArrayList<>();

          while ((gem = treasureRoomDoor.retrieveValuable()) != null) {
            gatheredGems.add(gem);
            totalGemsCollected += gem.getValue();

            if (totalGemsCollected >= partyCost) {
              break;
            }

            Thread.sleep(2000); // Simulate gem selection time
          }

          if (totalGemsCollected >= partyCost) {
            catalogue.log("King " + name + " collected enough gems: " + totalGemsCollected + ". THROWING PARTY! :)");
          } else {
            catalogue.log("King " + name + " didn't have enough gems... CANCELING PARTY :(");
            for (Gem v : gatheredGems) {
              treasureRoomDoor.addValuable(v);
            }
            Thread.sleep(10000); // Simulate time to put gems back
          }
        } finally {
          treasureRoomDoor.releaseWriteAccess(name);
        }

        Thread.sleep(4000); // King sleeps after the party
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        catalogue.log(name + " was interrupted from planning the party. Exiting...");
        break;
      }
    }
  }

//  @Override public void run()
//  {
//    while (true){
//      try{
//
//        int partyCost = random.nextInt(101) + 50; // between 50 and 150
//        Catalogue.getInstance().log("King " + name + " is planning a party! He needs " + partyCost + " worth of gems.");
//
//        treasureRoomDoor.acquireWriteAccess(name);
//
//        Gem gem;
//        int totalGemsCollected = 0;
//        List<Gem> gatheredGems = new ArrayList<>();
//
//        while ((gem = treasureRoomDoor.retrieveValuable())!=null)
//        {
//          gatheredGems.add(gem);
//          totalGemsCollected+=gem.getValue();
//
//          if(totalGemsCollected>=partyCost){
//            break;
//          }
//
//          //Select gem time simulation
//          Thread.sleep(2000);
//
//          if (totalGemsCollected >= partyCost)
//          {
//            Catalogue.getInstance().log("King "+name+" collected enough gems: "+totalGemsCollected+". THROWING PARTY! :) ");
//            System.out.println("King "+name+" collected enough gems: "+totalGemsCollected+". THROWING PARTY! :) ");
//          }else{
//            Catalogue.getInstance().log("King "+name+" didn't have enough gems... : "+totalGemsCollected+". CANCELING PARTY :( ");
//            System.out.println("King "+name+" didn't have enough gems... : "+totalGemsCollected+". CANCELING PARTY :( ");
//            for (Gem v: gatheredGems)
//            {
//              // Putting the gems back since they were not spent and simulate the time it takes to put them back
//              treasureRoomDoor.addValuable(v);
//            }
//            Catalogue.getInstance().log("Putting gems back...");
//            System.out.println("Putting gems back...");
//            Thread.sleep(10000);
//          }
//
//        }
//
//        treasureRoomDoor.releaseWriteAccess(name);
//        Catalogue.getInstance().log("King "+name+" is eepy and takes a nap (release write access).");
//        System.out.println("King "+name+" is eepy and takes a nap (release write access).");
//        Thread.sleep(4000);
//
//      }catch (InterruptedException e){
//        Thread.currentThread().interrupt();
//        Catalogue.getInstance().log(name + " was interrupted from planning the party. Exiting...");
//        e.printStackTrace();
//      }
//    }
//
//  }
}
