import java.util.Scanner;

/**
 * Calculates and compares the weight of an athlete with a chosen weight category.
 */
abstract class WeightCalculator {

  private static int weight; // current weight

  /**
   * Compares the athlete's current weight with the specified weight category.
   *
   * @param athlete The athlete object whose weight is being compared.
   * @param weightCategory The weight category to compare against.
   * @param scanner The Scanner object to read user input.
   * @return The athlete's current weight if it matches the category, or the updated weight if changed.
   */
  public static int compareWeight(
    Athlete athlete,
    WeightCategory weightCategory,
    Scanner scanner
  ) {
    if (
      athlete.getCurrentWeight() >= weightCategory.getLowerWeightLimit() &&
      athlete.getCurrentWeight() <= weightCategory.getUpperWeightLimit()
    ) {
      System.out.println(
        "Your current weight is " +
        athlete.getCurrentWeight() +
        " kg." +
        "\nYour chosen weight category is " +
        weightCategory +
        " - lower weight limit: " +
        weightCategory.getLowerWeightLimit() +
        "kg." +
        " - upper weight limit: " +
        weightCategory.getUpperWeightLimit() +
        "kg." +
        "\nYou fit the weight class perfectly."
      );
      return athlete.getCurrentWeight();
    } // Return the current weight if it matches the category
    else {
      System.out.println();
      System.out.println(
        "Your current weight and chosen competition's weight category do not match."
      );
      System.out.println(
        "Your chosen weight category is " +
        weightCategory +
        " - lower weight limit: " +
        weightCategory.getLowerWeightLimit() +
        "kg." +
        " - upper weight limit: " +
        weightCategory.getUpperWeightLimit() +
        "kg."
      );
      System.out.println(
        "Your current weight is " + athlete.getCurrentWeight() + " kg."
      );
      while (true) {
        System.out.println();
        System.out.print(
          "Do you want to change weight category or current weight? (Yes/No): "
        );
        String changeChoice = scanner.nextLine();
        if (
          changeChoice.equalsIgnoreCase("yes") ||
          changeChoice.equalsIgnoreCase("y")
        ) {
          int option = promptOption(scanner);
          if (option == 1) {
            weight = promptCurrentWeight(scanner);
            athlete.setCurrentWeight(weight); // Update the current weight
          } else if (option == 2) {
            weightCategory = promptWeightCategory(scanner);
            athlete.setWeightCategory(weightCategory); // Update the current weight category
          }
          // Recursively call compareWeight with the new values
          return compareWeight(athlete, athlete.getWeightCategory(), scanner);
        } else if (
          changeChoice.equalsIgnoreCase("no") ||
          changeChoice.equalsIgnoreCase("n")
        ) {
          System.out.println(
            "Your current weight is " +
            athlete.getCurrentWeight() +
            " kg." +
            "\nYour chosen weight category is " +
            weightCategory +
            " - lower weight limit: " +
            weightCategory.getLowerWeightLimit() +
            "kg." +
            " - upper weight limit: " +
            weightCategory.getUpperWeightLimit() +
            "kg." +
            "\nYour current weight and your chosen weight category do not match. \n You did not change the weight or chosen weight category"
          );
          return athlete.getCurrentWeight(); // Return the current weight if not changed
        } else {
          System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
        }
      }
    }
  }

  /**
   * Prompts the user to choose between changing the current weight or weight category.
   *
   * @param scanner The Scanner object to read user input.
   * @return The selected option (1 for changing weight, 2 for changing category).
   */
  private static int promptOption(Scanner scanner) {
    while (true) {
      System.out.println();
      System.out.println("Choose an option:");
      System.out.println("1. Change current weight");
      System.out.println("2. Change weight category");
      try {
        int option = Integer.parseInt(scanner.nextLine());
        if (option == 1 || option == 2) {
          return option;
        } else {
          System.out.println("Invalid option. Please choose 1 or 2.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  /**
   * Prompts the user to enter the current weight.
   *
   * @param scanner The Scanner object to read user input.
   * @return The entered current weight.
   */
  private static int promptCurrentWeight(Scanner scanner) {
    while (true) {
      System.out.println();
      System.out.print("Enter current weight: ");
      try {
        weight = Integer.parseInt(scanner.nextLine());
        if (weight >= 0 && weight <= 1000) {
          System.out.println(
            "Your have set your current weight to " + weight + " kg."
          );

          return weight;
        } else {
          System.out.println(
            "Invalid weight. Please enter a number between 0 and 1000."
          );
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }

  /**
   * Prompts the user to select a weight category.
   *
   * @param scanner The Scanner object to read user input.
   * @return The selected weight category.
   */
  private static WeightCategory promptWeightCategory(Scanner scanner) {
    while (true) {
      System.out.println();
      System.out.println("Choose a weight category:");
      for (int i = 0; i < WeightCategory.values().length; i++) {
        System.out.println(
          (i + 1) + ". " + WeightCategory.values()[i].getName()
        );
      }
      try {
        int categoryIndex = Integer.parseInt(scanner.nextLine());
        if (
          categoryIndex >= 1 && categoryIndex <= WeightCategory.values().length
        ) {
          System.out.println();
          System.out.println(
            "Your chosen weight category is " +
            WeightCategory.values()[categoryIndex - 1]
          );
          return WeightCategory.values()[categoryIndex - 1];
        } else {
          System.out.println(
            "Invalid category. Please choose a valid category."
          );
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input. Please enter a number.");
      }
    }
  }
}
