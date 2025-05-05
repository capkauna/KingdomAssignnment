package Kingdom;
import Gems.*;
import MiningStrategy.MiningStrategy;
import Logger.Catalogue;

public class GemMineWorker implements Runnable
{
  private final BlockingQueue<Gem> gemDeposit;
  private final MiningStrategy strategy;
  private Catalogue catalogue = Catalogue.getInstance();

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
        Gem gem = GemMine.mineGem();//there is no way a worker would know where a specific gem is, so they will decide whether they keep it or not, after mining
        if (strategy.shouldKeep(gem))
        {
          switch (gem.getGemType())
          {
            case COW ->
            {
              catalogue
                  .log("Who keeps bringing their cow to work?");
              System.out.println(gem.getName() + " found in the mine! Whose is this?");
            }

            case GHOST -> {
              catalogue
                  .log("This mine is haunted! Someone call the Ghostbusters!");
              System.out.println(gem.getName() + " found in the mine! Spooky!");
            }

            case PRINCESS -> {
              catalogue
                  .log(gem.getName() + "! This is no place for a princess!");
              System.out.println(gem.getName() + " found in the mine! Royalty spotted!");
            }

            case UNICORN -> {
              catalogue
                  .log(gem.getName() + " was sighted! It ran away somewhere...");
              System.out.println(gem.getName() + " was sighted! Nobody tell the King!");
              continue;//a unicorn cannot be contained, so the workers will let it go
            }

            case WOODENCOIN -> {
              catalogue
                  .log(gem.getName() + "... It keeps saying it's a real coin.");
              System.out.println(gem.getName() + "... who keeps making these?");
            }

            case GOLDNUGGET -> {
              catalogue
                  .log("Gold nugget! We're rich!");
              System.out.println(gem.getName() + "! We're RICH!");
            }
            case CHILD ->
                {
                  catalogue.log("The children yearn for the mines...");
                  System.out.println(gem.getName() + "?! Is our scary old man guard on lunch break again?");
                }

            default -> {
             catalogue.log("Gem mined: " + gem.getName());
              System.out.println(gem.getName() + " found in the mine.");
            }
          }
          gemDeposit.put(gem);
          strategy.rest();
        }
        else
        {
          System.out.println("This miner can't tell what this gem is... Gem discarded."); // For now
          Thread.sleep(2000);
        }
      }
      catch (InterruptedException e)
      {
        Thread.currentThread().interrupt();
        System.out.println("Miner is going home for the day.");
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
