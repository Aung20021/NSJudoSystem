import java.io.*;
import java.util.Scanner;

/**
 * Represents an athlete.
 */
public class Athlete implements Serializable {

  // Instance variables
  private String name; // The name of the athlete
  private TrainingPlan trainingPlan; // The training plan of the athlete
  private int currentWeight; // The current weight of the athlete in kilograms
  private int privateCoachingHours; // The number of private coaching hours per week
  private int competitionsEntered; // The number of competitions entered this month
  private WeightCategory weightCategory; // The weight category of the athlete

  /**
   * Default constructor.
   */
  public Athlete() {
    // Default constructor
  }

  // Getters

  /**
   * Gets the name of the athlete.
   *
   * @return The name category of the athlete.
   */
  public String getName() {
    return name;
  }

  /**
   * Gets the weight category of the athlete.
   *
   * @return The weight category of the athlete.
   */
  public WeightCategory getWeightCategory() {
    return weightCategory;
  }

  /**
   * Gets the weight of the athlete.
   *
   * @return The weight of the athlete.
   */
  public int getCurrentWeight() {
    return currentWeight;
  }

  // Setter
  /**
   * Setter method to update the current weight of the athlete.
   *
   * @param currentWeight The new current weight of the athlete.
   */
  public void setCurrentWeight(int currentWeight) {
    this.currentWeight = currentWeight;
  }

  /**
   * Setter method to update the weight category of the athlete.
   *
   * @param weightCategory The new weight category of the athlete.
   */
  public void setWeightCategory(WeightCategory weightCategory) {
    this.weightCategory = weightCategory;
  }

  /**
   * Override the toString method to display athlete information.
   * The format includes name, training plan, current weight, private coaching hours,
   * competitions entered, and weight category.
   *
   * @return A string representation of the athlete's information.
   */
  @Override
  public String toString() { // Override toString method to display athlete information
    return (
      "Athlete{" +
      "name='" +
      name +
      '\'' +
      ", Training Plan = " +
      trainingPlan +
      ", Current Weight = " +
      currentWeight +
      "kg" +
      ", Private CoachingHours = " +
      privateCoachingHours +
      "hr" +
      ", Competition(s) Entered = " +
      competitionsEntered +
      ", Weight Category = " +
      weightCategory +
      '}'
    );
  }

  // Method to register an athlete
  public void register() {
    // Flag to track if the private coaching question has been asked
    boolean privateCoachingQuestionAsked = false;
    // Variable to store the number of private coaching hours

    // Create a scanner object to read user input
    Scanner scanner = new Scanner(System.in);
    do {
      // Prompt the user to enter the athlete's name
      name = UIManager.promptString("Enter athlete's name: ", scanner);
      if (isValidName(name)) {
        System.out.println(
          "Invalid name. Please enter a valid name (no numbers, max 50 characters)."
        );
      } else if (name.length() <= 3) {
        System.out.println(
          "Name must be more than 3 characters. Please enter again."
        );
      } else {
        // Format the name with the first initial as a capital letter and the rest as lowercase
        name = formatName(name);
        // Display a line after successful name insertion
        System.out.println("Name successfully inserted: " + name);
        System.out.println();
      }
    } while (isValidName(name) || name.length() <= 3);

    // Display competition rules
    CompetitionRules.displayRules();
    System.out.println();

    do {
      // Select a training plan
      trainingPlan = UIManager.selectTrainingPlan(scanner);
      System.out.println();
      System.out.println("Selected training plan: " + trainingPlan.getName());
      System.out.println();
    } while (
      !UIManager.confirm(
        "Confirm " + trainingPlan.getName() + " training plan?",
        scanner
      )
    );
    System.out.println();

    // Select a weight category
    // Instance variable to store the athlete's weight category
    weightCategory = UIManager.selectWeightCategory(scanner);
    System.out.println();

    // Enter the athlete's current weight
    currentWeight =
      UIManager.promptInt("Enter current weight in kilograms: ", scanner);
    System.out.println("Your current weight is " + currentWeight + " kg.");
    System.out.println();

    // Ask for private coaching hours per week
    while (true) {
      if (!privateCoachingQuestionAsked) {
        String coachingChoice = UIManager.promptString(
          "Have you taken private coaching? (yes/no): ",
          scanner
        );
        if (
          coachingChoice.equalsIgnoreCase("yes") ||
          coachingChoice.equalsIgnoreCase("y")
        ) {
          privateCoachingQuestionAsked = true;
        } else if (
          coachingChoice.equalsIgnoreCase("no") ||
          coachingChoice.equalsIgnoreCase("n")
        ) {
          privateCoachingHours = 0;
          break;
        } else {
          System.out.println("Invalid choice. Please enter 'yes' or 'no'.");
          continue;
        }
      }

      privateCoachingHours =
        UIManager.promptInt(
          "Enter number of private coaching hours per week (maximum 5): ",
          scanner
        );
      if (privateCoachingHours >= 1 && privateCoachingHours <= 5) {
        break;
      } else {
        System.out.println(
          "Invalid input. Please enter a number between 1 and 5."
        );
      }
    }

    System.out.println();

    if (trainingPlan.equals(TrainingPlan.BEGINNER)) {
      competitionsEntered = 0; // Skip asking for competitions for the beginner plan
    } else {
      do {
        competitionsEntered =
          UIManager.promptInt(
            "Enter number of competitions entered this month: ", //  asking for competitions for intermediate and elite plans
            scanner
          );
        if (competitionsEntered < 0 || competitionsEntered > 3) { //Validation
          System.out.println(
            "Invalid input. Please enter a number between 0 and 3."
          );
        }
      } while (competitionsEntered < 0 || competitionsEntered > 3);
      System.out.println(
        "You have entered " + competitionsEntered + "competition(s) this month."
      );
    }
    System.out.println();

    // Compare weight with weight category
    WeightCalculator.compareWeight(this, weightCategory, scanner);

    // Display the cost of each item separately
    System.out.println();
    System.out.println("Athlete's name: " + name);
    System.out.println();
    CostCalculator.calculateTotalCost(
      trainingPlan,
      competitionsEntered,
      privateCoachingHours
    );
    System.out.println();

    // Serialize athlete object and write to file
    try (
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("athletes.dat", true)
      )
    ) {
      oos.writeObject(this);
    } catch (IOException e) {
      System.err.println("Error saving athlete information: " + e.getMessage());
    }
  }

  // Method to check if a name is valid
  private boolean isValidName(String name) {
    return !name.matches("^[a-zA-Z\\s]{1,50}$"); // Only alphabetic characters and spaces, max 50 characters
  }

  // Method to format a name
  private String formatName(String name) {
    String[] parts = name.toLowerCase().split("\\s");
    StringBuilder formattedName = new StringBuilder();
    for (String part : parts) {
      formattedName
        .append(Character.toUpperCase(part.charAt(0)))
        .append(part.substring(1))
        .append(" ");
    }
    return formattedName.toString().trim();
  }
}
