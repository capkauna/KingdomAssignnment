package MiningStrategy;

import Gems.Gem;

public class MineSlow implements MiningStrategy
{
  @Override public boolean shouldKeep(Gem gem)
  {
    return true;
  }

  @Override public void rest() throws InterruptedException
  {
    Thread.sleep(10000);
  }

}
