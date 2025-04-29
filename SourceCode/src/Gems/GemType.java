package Gems;

public enum GemType
{
  DIAMOND("Diamond", 100, 3),
  EMERALD("Emerald", 50, 40),
  RUBY("Ruby", 30, 55),
  SAPPHIRE("Sapphire", 70, 35),
  AMETHYST("Amethyst", 20, 100),
  GOLDNUGGET("Gold Nugget", 10, 165),
  WOODENCOIN("Wooden Coin", 1, 185),
  COW ("Cow", -1, 15),
  PRINCESS("Princess", -12, 3),
  UNICORN("Unicorn", -35, 1),
  GHOST("Scary ghost", -3,66);


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
