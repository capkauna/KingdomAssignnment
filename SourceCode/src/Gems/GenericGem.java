package Gems;

public class GenericGem implements Gem {
  private final String name;
  private final int value;

  public GenericGem(GemType type) {
    this.name = type.getName();
    this.value = type.getValue();
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getValue() {
    return value;
  }
}
