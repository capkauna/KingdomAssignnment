package MiningStrategy;
import Gems.Gem;

public interface MiningStrategy
{
  boolean shouldKeep(Gem gem);//determine if the gem should be mined
  void rest() throws InterruptedException;//determine mining speed
}
