import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class represents the main program for athlete registration and information management.
 */
public class Program {

  /**
   * The main entry point for the program.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    UIManager.displayWelcomeMessage();
    List<Athlete> athletes = new ArrayList<>();
    do { // Register athletes
      Athlete athlete = new Athlete();
      athlete.register();
      athletes.add(athlete);
    } while (
      UIManager.confirm("Do you want to register another athlete?", scanner)
    );
    saveAthletesToFile(athletes); // Save athletes' information to file
    System.out.println("Registration complete. Thank you!");
    System.out.println();
    // Ask user if they want to view athlete information
    if (
      UIManager.confirm(
        "Do you want to view registered athletes' information?",
        scanner
      )
    ) {
      System.out.println();
      int choice;
      do {
        System.out.println("Select an option:");
        System.out.println("1. Show all athletes' information");
        System.out.println("2. Show individual athlete information");
        System.out.println("0. Exit");
        System.out.println();
        choice = UIManager.promptInt("Enter choice: ", scanner);
        System.out.println();

        switch (choice) {
          case 1 -> showAllAthletesInformation();
          case 2 -> showIndividualAthleteInformation(athletes, scanner);
          case 0 -> System.out.println("Exiting program.");
          default -> System.out.println("Invalid choice. Please try again.");
        }
      } while (choice != 0);
    }
    scanner.close();
  }

  /**
   * Saves the list of athletes to a file.
   *
   * @param athletes The list of athletes to save.
   */
  private static void saveAthletesToFile(List<Athlete> athletes) {
    try (
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("athletes.dat")
      )
    ) {
      oos.writeObject(athletes);
      System.out.println("Athlete information saved successfully.");
    } catch (IOException e) {
      System.err.println("Error saving athlete information: " + e.getMessage());
    }
  }

  /**
   * Reads the list of athletes from a file.
   *
   * @return The list of athletes read from the file.
   */
  @SuppressWarnings("unchecked")
  private static List<Athlete> readAthletesFromFile() {
    try (
      ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream("athletes.dat")
      )
    ) {
      return (List<Athlete>) ois.readObject();
    } catch (IOException | ClassNotFoundException e) {
      System.err.println(
        "Error reading athlete information: " + e.getMessage()
      );
    }
    return new ArrayList<>();
  }

  /**
   * Displays information for all registered athletes.
   */
  private static void showAllAthletesInformation() {
    List<Athlete> athletes = readAthletesFromFile();
    System.out.println("Number of registered athletes: " + athletes.size());
    System.out.println("All athletes' information:");
    for (Athlete athlete : athletes) {
      System.out.println(athlete);
    }
    System.out.println();
  }

  /**
   * Displays information for a specific athlete.
   *
   * @param athletes The list of athletes to search for the specific athlete.
   * @param scanner  The scanner object used for user input.
   */
  private static void showIndividualAthleteInformation(
    List<Athlete> athletes,
    Scanner scanner
  ) {
    System.out.println("Number of registered athletes: " + athletes.size());
    System.out.println("List of athlete names:");
    for (Athlete athlete : athletes) {
      System.out.println(athlete.getName());
    }
    System.out.println();
    System.out.print("Enter athlete name: ");

    String name = scanner.nextLine();
    boolean found = false;
    for (Athlete athlete : athletes) {
      if (athlete.getName().equalsIgnoreCase(name)) {
        System.out.println();
        System.out.println("Athlete information:");
        System.out.println(athlete);
        System.out.println();
        found = true;
        break;
      }
    }
    if (!found) {
      System.out.println("Athlete not found.");
    }
  }
}
