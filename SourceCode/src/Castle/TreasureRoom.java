package Castle;
import Gems.Gem;

import java.util.ArrayList;
import java.util.List;

public class TreasureRoom implements TreasureRoomDoor {
  private List<Gem> gems;

  public TreasureRoom() {
    gems = new ArrayList<>();
  }

  //Swap out the comments in the methods with relevant calls to the Logger.Catalogue

  // access methods

  public synchronized void acquireReadAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " was allowed access to looking at the Treasure Room.");
  }

  public synchronized void acquireWriteAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " was given handling access to the Treasure Room.");
  }

  public synchronized void releaseReadAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " finished looking through the Treasure Room.");
  }

  public synchronized void releaseWriteAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " finished managing the Treasure Room.");
  }

  // interact methods

  public Gem retrieveValuable() {
    Gem v = null;
    if (gems.size() > 0) {
      v = gems.remove(0);
    }
    if (v == null) {
      Logger.Catalogue.getInstance().log("No gems in the Treasure Room.");
      return null;
    }
    Logger.Catalogue.getInstance().log(v.getName() + " was taken from the Treasure Room.");
    return v;
  }

  public void addValuable(Gem v) {
    gems.add(v);
    Logger.Catalogue.getInstance().log(v.getName() + " was added to the Treasure Room.");
  }

  @Override
  public List<Gem> lookAtAllGems() {
    Logger.Catalogue.getInstance().log("Looking at all the gems in the Treasure Room." + gems.size() + " gems found.");
    return new ArrayList<Gem>(gems);
  }
}