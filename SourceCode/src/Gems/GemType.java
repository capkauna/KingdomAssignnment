package Gems;

public enum GemType
{
  DIAMOND("Diamond", 100, 3),
  EMERALD("Emerald", 50, 40),
  RUBY("Ruby", 30, 55),
  SAPPHIRE("Sapphire", 70, 35),
  AMETHYST("Amethyst", 20, 100),
  GOLDNUGGET("Gold Nugget", 30, 165),
  WOODENCOIN("A wooden coin", 1, 100),
  COW ("Cow", -1, 15),
  PRINCESS("A princess", -20, 3),
  UNICORN("Unicorn", -350, 1),
  GHOST("Scary ghost", -3,16),
  CHILD("A child", -10, 7),;


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
