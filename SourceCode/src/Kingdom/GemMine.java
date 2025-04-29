package Kingdom;
import Gems.*;
import MyArrayList.MyArrayList;

import java.util.*;

public class GemMine
{

  /*
  responsible for creating Gem objects with different values
  can be multiton-like (the list of objects is accessed through the GemMine but the objects are of type Gem)
  can be a simple collection instance that all workers are assigned to in their constructor

  In relevant places you should have the Catalogue print out what is happening so you can follow along.
   */

  private static final Map<String, Gem> gemMap = new HashMap<>();
  private static final List<Gem> weightedGems = new ArrayList<>();
  private static final Random random = new Random();

  static {
    for (GemType type : GemType.values()) {
      GenericGem gg = new GenericGem(type);
      gemMap.put(type.getName(), gg);

      //affecting the gem numbers by weight
      for (int i = 0; i < type.getWeight(); i++) {
        weightedGems.add(gg);
      }
    }
  }

  public static Gem getGem(String name) {
    return gemMap.get(name);
  }
  public static Gem mineGem(){
    int index = random.nextInt(weightedGems.size());
    return weightedGems.get(index);
  }


}
