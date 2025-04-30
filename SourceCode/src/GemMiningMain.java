/*
    instantiate a GemDeposit,
    start a couple of GemMineWorkers (in separate threads with a reference to the GemDeposit)
    and start a couple of GemTransporters too.

    at the end of step 2 - expand to now include the TreasureRoom, TreasureRoomGuard, Accountant, and King.

Run the program and inspect the console output.
     */


import Castle.TreasureRoomDoor;
import MiningStrategy.*;
import Kingdom.*;
import Logger.Catalogue;
import Gems.*;

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
    MiningStrategy goldStrategy = new MineSpecificGem(GemType.GOLDNUGGET);
    MiningStrategy casualStrategy = new MineNormal();

    // Create workers
    GemMineWorker worker1 = new GemMineWorker(gemDeposit, fastStrategy);
    GemMineWorker worker2 = new GemMineWorker(gemDeposit, slowStrategy);
    GemMineWorker worker3 = new GemMineWorker(gemDeposit, goldStrategy);
    GemMineWorker worker4 = new GemMineWorker(gemDeposit, casualStrategy);

    // Create transporters
    GemTransporter transporter1 = new GemTransporter("Cart 1", gemDeposit, dummyDoor);
    GemTransporter transporter2 = new GemTransporter("Cart 2", gemDeposit, dummyDoor);

    // Start threads
    Thread w1 = new Thread(worker1, "Worker 1");
    Thread w2 = new Thread(worker2, "Worker 2");
    Thread w3 = new Thread(worker3, "Worker 3");
    Thread w4 = new Thread(worker4, "Worker 4");
    Thread t1 = new Thread(transporter1, "Transporter A");
    Thread t2 = new Thread(transporter2, "Transporter B");

    w1.start();
    w2.start();
    w3.start();
    w4.start();
    t1.start();
    t2.start();

    log.log("All workers and transporters started.");
  }
}



