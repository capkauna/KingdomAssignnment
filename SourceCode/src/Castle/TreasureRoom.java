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
    Logger.Catalogue.getInstance().log(actorName + " acquired READ access to the Treasure Room.");
  }

  public synchronized void acquireWriteAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " acquired WRITE access to the Treasure Room.");
  }

  public synchronized void releaseReadAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " released READ access to the Treasure Room.");
  }

  public synchronized void releaseWriteAccess(String actorName) {
    Logger.Catalogue.getInstance().log(actorName + " released WRITE access to the Treasure Room.");
  }

  // interact methods

  public Gem retrieveValuable() {
    Gem v = null;
    if (gems.size() > 0) {
      v = gems.remove(0);
    }
    return v;
  }

  public void addValuable(Gem v) {
    gems.add(v);
  }

  @Override
  public List<Gem> lookAtAllGems() {
    return new ArrayList<Gem>(gems);
  }
}