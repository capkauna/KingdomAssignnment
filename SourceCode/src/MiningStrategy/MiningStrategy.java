package MiningStrategy;
import Gems.Gem;

public interface MiningStrategy
{
  boolean shouldMine(Gem gem);
  void rest() throws InterruptedException;
}
