/**
 * Enum representing weight categories for athletes.
 */
enum WeightCategory {
  FLYWEIGHT("Flyweight", 0, 66),
  LIGHTWEIGHT("Lightweight", 67, 73),
  LIGHT_MIDDLEWEIGHT("Light-Middleweight", 74, 81),
  MIDDLEWEIGHT("Middleweight", 82, 90),
  LIGHT_HEAVYWEIGHT("Light-Heavyweight", 91, 100),
  HEAVYWEIGHT("Heavyweight", 101, 999);

  private final String name;
  private final int lowerWeightLimit;
  private final int upperWeightLimit;

  /**
   * Constructor for WeightCategory enum.
   *
   * @param name The name of the weight category.
   * @param lowerWeightLimit The lower weight limit for the category.
   * @param upperWeightLimit The upper weight limit for the category.
   */
  WeightCategory(String name, int lowerWeightLimit, int upperWeightLimit) {
    this.name = name;
    this.lowerWeightLimit = lowerWeightLimit;
    this.upperWeightLimit = upperWeightLimit;
  }

  /**
   * Returns the name of the weight category.
   *
   * @return The name of the weight category.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the lower weight limit for the category.
   *
   * @return The lower weight limit for the category.
   */
  public int getLowerWeightLimit() {
    return lowerWeightLimit;
  }

  /**
   * Returns the upper weight limit for the category.
   *
   * @return The upper weight limit for the category.
   */
  public int getUpperWeightLimit() {
    return upperWeightLimit;
  }
}
