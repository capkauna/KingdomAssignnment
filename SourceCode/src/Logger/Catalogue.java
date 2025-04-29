package Logger;

import java.io.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Catalogue
{
  private File catalogueFile;
  private static Catalogue instance;
  private static Lock lock = new ReentrantLock();
  private int eventCount = 0; //added this to have a counter for each event logged (aesthetics)

  private Catalogue() {
    catalogueFile = new File("Logger.Catalogue.txt");
    if (catalogueFile.exists())
    {
      try (BufferedReader reader = new BufferedReader(new FileReader(catalogueFile)))
          //this makes sure that the counter doesn't reset between sessions
      {
        while (reader.readLine() != null)
        {
          eventCount++;
        }
      }
      catch (IOException e)
      {
        throw new RuntimeException(e);
      }

    }
  }

  public static synchronized Catalogue getInstance() {
    if (instance == null) {
      synchronized (lock) {
        if (instance == null)
         {instance = new Catalogue();}
      }
    }
    return instance;
  }

  //template
  public synchronized void log(String message) {
    try {
      Writer out = new BufferedWriter(new FileWriter(catalogueFile, true));
      eventCount++;
      out.append(eventCount + ". " + message + "\n");
      out.flush();
      out.close();
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
  }



  //instances for:
  // when and how much GemTransporter add to TreasureRoom;
  //when King holds party or cancels it;
  //when Accountants calculated the total wealth in TreasureRoom
  //or include logs for when one or more of them are waiting to enter TreasureRoom
}
