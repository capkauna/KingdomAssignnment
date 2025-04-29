package Castle;
import Kingdom.*;

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

  @Override public void run()
  {

  }
}
