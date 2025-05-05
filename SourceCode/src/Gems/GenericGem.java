package Gems;

public class GenericGem implements Gem {
  //making a generic class so all the information is added once in the enum
  //and copied upon object creation, so conflicting values are less likely to occur
  //open-close principle
  private final String name;
  private final int value;
  private final int weight;
  private final GemType gemType;

  public GenericGem(GemType type) {
    this.name = type.getName();
    this.value = type.getValue();
    this.weight = type.getWeight();
    this.gemType = type;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public int getValue() {
    return value;
  }
  @Override
  public int getWeight() {
    return weight;
  }
  @Override
  public GemType getGemType() {
    return gemType;
  }
  @Override
  public String toString() {
    return "Gem{" +
            "name='" + name + '\'' +
            ", value=" + value +
            ", gemType=" + gemType.toString() +
            '}';
  }
}
