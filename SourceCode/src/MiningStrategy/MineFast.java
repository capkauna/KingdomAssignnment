package MiningStrategy;

import Gems.Gem;

public class MineFast implements MiningStrategy
{

  @Override public boolean shouldKeep(Gem gem)
  {
    return true;
  }

  @Override public void rest() throws InterruptedException
  {
    Thread.sleep(1000);
  }
}
