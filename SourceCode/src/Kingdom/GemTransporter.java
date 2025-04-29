package Kingdom;

public class GemTransporter implements Runnable
{

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

  }
}
