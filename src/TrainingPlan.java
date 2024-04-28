/**
 * Enum representing different training plans.
 */
enum TrainingPlan {
  BEGINNER("Beginner", 25.00),
  INTERMEDIATE("Intermediate", 30.00),
  ELITE("Elite", 35.00);

  private final String name; // The name of the training plan

  private final double weeklyFee; // The weekly fee for the training plan

  /**
   * Constructor for TrainingPlan enum.
   *
   * @param name      The name of the training plan.
   * @param weeklyFee The weekly fee for the training plan.
   */
  TrainingPlan(String name, double weeklyFee) {
    this.name = name;
    this.weeklyFee = weeklyFee;
  }

  /**
   * Returns the name of the training plan.
   *
   * @return The name of the training plan.
   */
  public String getName() {
    return name;
  }

  /**
   * Returns the weekly fee for the training plan.
   *
   * @return The weekly fee for the training plan.
   */
  public double getWeeklyFee() {
    return weeklyFee;
  }

  /**
   * Returns the TrainingPlan enum instance based on the provided name.
   *
   * @param name The name of the training plan to retrieve.
   * @return The TrainingPlan enum instance corresponding to the provided name.
   * @throws IllegalArgumentException if the provided name does not match any training plan.
   */
  public static TrainingPlan getTrainingPlanByName(String name)
    throws IllegalArgumentException {
    for (TrainingPlan plan : TrainingPlan.values()) {
      if (plan.getName().equalsIgnoreCase(name)) {
        return plan;
      }
    }
    throw new IllegalArgumentException("Invalid training plan name: " + name);
  }
}
