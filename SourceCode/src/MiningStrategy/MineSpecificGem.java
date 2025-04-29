package MiningStrategy;

import Gems.Gem;
import Gems.GemType;
import Kingdom.GemMine;

public class MineSpecificGem implements MiningStrategy
{
  private final GemType gemType;

  public MineSpecificGem (GemType gemType)
  {
    this.gemType = gemType;

  }


  @Override public boolean shouldMine(Gem gem)
  {
    return gem.getName().equalsIgnoreCase(gemType.getName());
  }

  @Override public void rest() throws InterruptedException
  {
    Thread.sleep(1000);
  }
}
