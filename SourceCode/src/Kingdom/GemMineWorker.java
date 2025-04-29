package Kingdom;
import Gems.*;
import MiningStrategy.MiningStrategy;
import Logger.Catalogue;

public class GemMineWorker implements Runnable
{
  private final BlockingQueue<Gem> gemDeposit;
  private final MiningStrategy strategy;

  public GemMineWorker(BlockingQueue<Gem> gemDeposit, MiningStrategy strategy)
  {
    this.gemDeposit = gemDeposit;
    this.strategy = strategy;
  }


  @Override public void run()
  {
    while(true)
    {
      try
      {
        Gem gem = GemMine.mineGem();
        if (strategy.shouldMine(gem))
        {
          gemDeposit.put(gem);
          switch (gem.getGemType())
          {
            case COW -> Catalogue.getInstance().log("Who keeps bringing their cow to work?");
            case GHOST -> Catalogue.getInstance().log("This mine is haunted! Someone call the Ghostbusters!");
            case PRINCESS -> Catalogue.getInstance().log("This is no place for a princess!");
            case UNICORN -> Catalogue.getInstance().log("Are there any princesses here? Why is there a unicorn?");
            case WOODENCOIN -> Catalogue.getInstance().log("A wooden coin... It keeps saying it's a real coin.");
            case GOLDNUGGET -> Catalogue.getInstance().log("Gold nugget! We're rich!");
            default -> Catalogue.getInstance().log("Gem mined: " + gem.getName());
          }
          strategy.rest();
        }
        else
        {
          System.out.println("Gem thrown.");
          Thread.sleep(200);
        }
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt();
        System.out.println("Miner interrupted");
        break;
      }
      catch (Exception e)
      {
        System.out.println("Miner is under " + e.getMessage() + " confusion spell");
      }
    }

  }

  /*
  must have a while(true) loop in which it will get a Gem from the GemMine and insert this Gem into the GemDeposit (our blocking queue)
  should have different Strategies for mining (mine fast, mine slow, only mine specific Gem types, etc)
  concrete strategies vary in time, quantity and quality of gem production

   */
}
