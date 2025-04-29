/*
    instantiate a GemDeposit,
    start a couple of GemMineWorkers (in separate threads with a reference to the GemDeposit)
    and start a couple of GemTransporters too.

    at the end of step 2 - expand to now include the TreasureRoom, TreasureRoomGuard, Accountant, and King.

Run the program and inspect the console output.
     */


import Castle.TreasureRoomDoor;
import MiningStrategy.*;
import Kingdom.GemDeposit;
import Kingdom.GemMineWorker;
import Kingdom.GemTransporter;
import Logger.Catalogue;
import MiningStrategy.MiningStrategy;

public class GemMiningMain{
  public static void main(String[] args)
  {
    Catalogue log = Catalogue.getInstance();
    log.log("=== GEM MINING SIMULATION STARTED ===");

    GemDeposit gemDeposit = new GemDeposit(20); // capacity = 20
    TreasureRoomDoor dummyDoor = new DummyTreasureRoom();

    // Create mining strategies
    MiningStrategy fastStrategy = new MineFast();
    MiningStrategy slowStrategy = new MineSlow();

    // Create workers
    GemMineWorker worker1 = new GemMineWorker(gemDeposit, fastStrategy);
    GemMineWorker worker2 = new GemMineWorker(gemDeposit, slowStrategy);

    // Create transporters
    GemTransporter transporter1 = new GemTransporter(gemDeposit, dummyDoor);
    GemTransporter transporter2 = new GemTransporter(gemDeposit, dummyDoor);

    // Start threads
    Thread w1 = new Thread(worker1, "Worker 1");
    Thread w2 = new Thread(worker2, "Worker 2");
    Thread t1 = new Thread(transporter1, "Transporter A");
    Thread t2 = new Thread(transporter2, "Transporter B");

    w1.start();
    w2.start();
    t1.start();
    t2.start();

    log.log("All workers and transporters started.");
  }
}



