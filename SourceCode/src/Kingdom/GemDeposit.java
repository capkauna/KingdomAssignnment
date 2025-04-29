package Kingdom;

import Gems.Gem;
import Logger.Catalogue;
import MyArrayList.MyArrayList;

public class GemDeposit implements BlockingQueue<Gem>
{

  /*
  used by the GemMineWorker (producer) and GemTransporter (consumer)
  must use MyArrayList for the implementation of a blocking queue
  should use the Adapter pattern to convert the MyArrayList into something that acts like a BlockingQueue
   */

}
