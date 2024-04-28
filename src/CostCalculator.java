import java.text.DecimalFormat;

abstract class CostCalculator {

  /**
   * Calculates the total cost for an athlete's training plan, competitions, and private coaching.
   * Prints the breakdown of costs for each item and the total cost for the month.
   *
   * @param trainingPlan               The selected training plan for the athlete.
   * @param competitionsEntered        The number of competitions the athlete has entered for the month.
   * @param privateCoachingHoursPerWeek The number of private coaching hours the athlete selected per week.
   */
  public static void calculateTotalCost(
    TrainingPlan trainingPlan,
    int competitionsEntered,
    int privateCoachingHoursPerWeek
  ) { // Create a decimal format for displaying currency values
    DecimalFormat df = new DecimalFormat("#.00");

    double trainingPlanCost = trainingPlan.getWeeklyFee() * 4; // Calculate the cost of the training plan for the month
    double competitionsCost = competitionsEntered * 22.00; // Calculate the cost of competitions for the month
    double coachingCost = Math.min(privateCoachingHoursPerWeek, 5) * 9.00 * 4; // Calculate the cost of private coaching for the month, limited to a maximum of 5 hours per week

    // Display the breakdown of costs for the training plan
    System.out.println("Training Plan Cost:");
    System.out.println(
      trainingPlan.getName() +
      " $" +
      df.format(trainingPlan.getWeeklyFee()) +
      " per week => 4 weeks per month => Total $" +
      df.format(trainingPlanCost)
    );
    // Display the breakdown of costs for competitions
    System.out.println("\nCompetitions Cost:");
    System.out.println(
      "$22.00 per Competition => " +
      competitionsEntered +
      " entered this month => Total $" +
      df.format(competitionsCost)
    );
    // Display the breakdown of costs for private coaching
    System.out.println("\nPrivate Coaching Cost:");
    System.out.println(
      "$9.00 per hour => selected " +
      privateCoachingHoursPerWeek +
      " hr per week => 4 weeks per month => Total $" +
      df.format(coachingCost)
    );
    // Calculate and display the total cost for the month
    double totalCost = trainingPlanCost + competitionsCost + coachingCost;
    System.out.println("\nTotal cost for this month $" + df.format(totalCost));
  }
}
