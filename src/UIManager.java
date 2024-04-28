import java.util.Scanner;

/**
 * Manages user interface interactions for the North Sussex Judo training system.
 */
class UIManager {

  /**
   * Displays a welcome message to the user.
   */
  public static void displayWelcomeMessage() {
    System.out.println("Welcome to the North Sussex Judo training System");
  }

  /**
   * Allows the user to select a training plan from available options.
   *
   * @param scanner The Scanner object to read user input.
   * @return The selected TrainingPlan.
   */
  public static TrainingPlan selectTrainingPlan(Scanner scanner) {
    while (true) {
      System.out.println("Select a training plan:");
      for (TrainingPlan plan : TrainingPlan.values()) {
        System.out.println(
          plan.getName() + " - weekly fee: $" + plan.getWeeklyFee()
        );
      }
      String planName = promptString("Enter training plan name: ", scanner);
      try {
        return TrainingPlan.getTrainingPlanByName(planName);
      } catch (IllegalArgumentException e) {
        System.out.println();
        System.out.println(
          "Invalid training plan. Please select from the options."
        );
      }
    }
  }

  /**
   * Allows the user to select a weight category from available options.
   *
   * @param scanner The Scanner object to read user input.
   * @return The selected WeightCategory.
   */
  public static WeightCategory selectWeightCategory(Scanner scanner) {
    System.out.println("Select a weight category:");
    for (WeightCategory category : WeightCategory.values()) {
      System.out.println(
        category.getName() +
        " - lower weight limit: " +
        category.getLowerWeightLimit() +
        "kg" +
        " - upper weight limit: " +
        category.getUpperWeightLimit() +
        "kg"
      );
    }

    while (true) {
      String categoryName = promptString(
        "Enter weight category name: ",
        scanner
      );
      // Remove spaces and dashes to make it easier for the user to input
      categoryName = categoryName.replaceAll("[\\s-]", "");
      categoryName = categoryName.toUpperCase();

      // Check if the input matches any weight category
      for (WeightCategory category : WeightCategory.values()) {
        if (
          categoryName.equals(
            category.getName().replaceAll("[\\s-]", "").toUpperCase()
          )
        ) {
          System.out.println("You have chosen " + category.getName() + ".");
          return category;
        }
      }

      // If no match found, ask the user to input again
      System.out.println(
        "Invalid input. Please enter a valid weight category name."
      );
    }
  }

  /**
   * Prompts the user to enter an integer value.
   *
   * @param message The message to display to the user.
   * @param scanner The Scanner object to read user input.
   * @return The integer value entered by the user.
   */
  public static int promptInt(String message, Scanner scanner) {
    while (true) {
      System.out.print(message);
      String input = scanner.nextLine();
      try {
        int number = Integer.parseInt(input);
        if (number >= 0) {
          return number;
        } else {
          System.out.println("Invalid input. Please enter a positive integer.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a valid integer.");
      }
    }
  }

  /**
   * Prompts the user to enter a string value.
   *
   * @param message The message to display to the user.
   * @param scanner The Scanner object to read user input.
   * @return The string entered by the user.
   */
  public static String promptString(String message, Scanner scanner) {
    System.out.print(message);
    return scanner.nextLine();
  }

  /**
   * Prompts the user to confirm an action.
   *
   * @param message The message to display to the user.
   * @param scanner The Scanner object to read user input.
   * @return true if the user confirms, false otherwise.
   */
  public static boolean confirm(String message, Scanner scanner) {
    while (true) {
      System.out.print(message + " (yes/no): ");
      String choice = scanner.nextLine();
      if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {
        return true;
      } else if (
        choice.equalsIgnoreCase("no") || choice.equalsIgnoreCase("n")
      ) {
        return false;
      } else {
        System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
      }
    }
  }
}
