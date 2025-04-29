package Gems;

public enum GemType
{
  DIAMOND("Diamond", 100, 1),
  EMERALD("Emerald", 50, 10),
  RUBY("Ruby", 30, 15),
  SAPPHIRE("Sapphire", 70, 5),
  AMETHYST("Amethyst", 20, 50),
  GOLDNUGGET("Gold Nugget", 10, 65),
  WOODENCOIN("Wooden Coin", 1, 85),
  COW ("Cow", -1, 3),
  GHOST("Scary ghost", -3,2);


  private final String name;
  private final int value;
  private final int weight; //added this to add a probability of finding a gem,
                            // so the miners can't just summon whatever gems they please

  GemType(String name, int value, int weight) {
    this.name = name;
    this.value = value;
    this.weight = weight;
  }

  public String getName() {
    return name;
  }

  public int getValue() {
    return value;
  }
  public int getWeight() {
    return weight;
  }
}
