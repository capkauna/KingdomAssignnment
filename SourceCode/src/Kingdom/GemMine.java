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

  In relevant places you should have the Logger.Catalogue print out what is happening so you can follow along.
   */

  private static final Map<String, Gem> gemMap = new HashMap<>();
  private static final List<Gem> weightedGems = new ArrayList<>();
  private static final Random random = new Random();

  static {
    for (GemType type : GemType.values()) {
      GenericGem gg = new GenericGem(type);
      gemMap.put(type.getName(), gg);

      //affecting the gem numbers by total weight
      for (int i = 0; i < type.getWeight(); i++) {
        weightedGems.add(gg);
      }
    }
  }

  public static Gem getGem(String name) {
    return gemMap.get(name);//this returns the exact gem object, but that's boring and unrealistic
  }
  public static Gem mineGem(){
    int index = random.nextInt(weightedGems.size());//this is far more believable and fun
    return weightedGems.get(index);
  }


}
