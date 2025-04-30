package Backlog;

import Gems.Gem;

public class DummyTreasureRoomDoor implements Castle.TreasureRoomDoor
{
  @Override public java.util.List<Gems.Gem> lookAtAllGems()
  {
    return new java.util.ArrayList<>();
  }

  @Override public void acquireReadAccess(String name)
  {
  }

  @Override public void releaseReadAccess(String name)
  {
  }

  @Override public void acquireWriteAccess(String name)
  {
  }

  @Override public void releaseWriteAccess(String name)
  {
  }

  @Override public Gem retrieveValuable()
  {
    return null;
  }

  @Override public void addValuable(Gem v)
  {
  }
}