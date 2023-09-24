import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShowBookingApp {
    public static void main(String[] args) {
        List<Show> shows = new ArrayList<>();
        Admin admin = new Admin();
        Buyer buyer = new Buyer();

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAre you an Admin or Buyer:");
            System.out.println("1. Admin");
            System.out.println("2. Buyer");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    // Handle Admin menu
                    adminCmds(scanner, admin, shows);
                    break;
                case 2:
                    // Handle Buyer menu
                    buyerCmds(scanner, buyer, shows);
                    break;
                case 3:
                    exit = true; // Exit the program
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
        System.out.println("Exiting the application. Goodbye!");
    }

    private static void adminCmds(Scanner scanner, Admin admin, List<Show> shows) {
        // Implement the Admin menu options here
        System.out.println("Admin menu placeholder.");

        while (true) {
            System.out.println("\nAdmin Commands:");
            System.out.println("Setup or View shows e.g., 'Setup 1 10 5 120' or 'View 1'");
            System.out.println("Enter 3 to return.");

            System.out.print("Enter your command: ");
            String input = scanner.nextLine().toLowerCase();

            String[] commandParts = input.split(" ");
            String command = commandParts[0];

            switch (command.toLowerCase()) {
                case "setup":
                    int showNumber = Integer.parseInt(commandParts[1]);
                    int numRows = Integer.parseInt(commandParts[2]);
                    int seatsPerRow = Integer.parseInt(commandParts[3]);
                    int cancellationWindow = Integer.parseInt(commandParts[4]);
                    admin.setupShow(shows, showNumber, numRows, seatsPerRow, cancellationWindow);
                    break;
                case "view":
                    Show show = findShowByNumber(shows, Integer.parseInt(commandParts[1]));
                    admin.viewShow(show);
                    break;
                case "3":
                    return; // Return to the main menu
                default:
                    // Handle other commands or show an error message for invalid input
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static void buyerCmds(Scanner scanner, Buyer buyer, List<Show> shows) {
        while (true) {
            int showNumber = 0;
            Show show = null;

            System.out.println("\nBuyer Commands:");
            System.out.println("Availablility, Book, Cancel");
            System.out.println("Enter 3 to return.");

            System.out.print("Enter your command: ");
            String input = scanner.nextLine().toLowerCase();

            String[] commandParts = input.split(" ");
            String command = commandParts[0];

            switch (command.toLowerCase()) {
                case "availability":
                    showNumber = Integer.parseInt(commandParts[1]);
                    show = findShowByNumber(shows, showNumber);
                    buyer.checkSeatAvailability(show);
                    break;
                case "book":
                    showNumber = Integer.parseInt(commandParts[1]);
                    show = findShowByNumber(shows, showNumber);
                    String phone = commandParts[2];
                    String seatsPerRow = commandParts[3];
                    buyer.bookSeats(show, phone, seatsPerRow);
                    break;
                case "cancel":
                    break;
                case "3":
                    return; // Return to the main menu
                default:
                    // Handle other commands or show an error message for invalid input
                    System.out.println("Invalid command. Please try again.");
            }
        }
    }

    private static Show findShowByNumber(List<Show> shows, int showNumber) {
        for (Show show : shows) {
            if (show.getShowNumber() == showNumber) {
                return show;
            }
        }
        return null;
    }
}
