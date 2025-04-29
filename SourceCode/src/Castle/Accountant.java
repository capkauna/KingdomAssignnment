package Castle;
import Kingdom.*;

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

  @Override public void run()
  {

  }

}
