import java.util.Scanner;

public class ShowBookingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        Buyer buyer = new Buyer();

        while (true) {
            System.out.println("Enter your role (admin/buyer):");
            String role = scanner.nextLine().trim().toLowerCase();

            if ("admin".equals(role)) {
                adminCmds(scanner, admin);
            } else if ("buyer".equals(role)) {
                buyerCmds(scanner, buyer);
            } else if ("exit".equals(role)) {
                System.exit(0);
            } else {
                System.out.println("Invalid role. Please enter 'admin', 'buyer' or 'exit.");
            }
        }
    }

    private static void adminCmds(Scanner scanner, Admin admin) {
        while(true) {
            System.out.println("Setup shows with: Setup <Show Number> <Number of Rows> <Number of seats per row> <Cancellation window in minutes>");
            System.out.println("View shows with: View <Show Number>");

            String input = scanner.nextLine().trim();
            String[] parts = input.split(" ");
            String command = parts[0].toLowerCase();

            switch (command) {
                case "setup":
                    if (parts.length == 5) {
                        try {
                            int showNumber = Integer.parseInt(parts[1]);
                            int numRows = Integer.parseInt(parts[2]);
                            int seatsPerRow = Integer.parseInt(parts[3]);
                            int cancellationWindow = Integer.parseInt(parts[4]);
                            admin.setupShow(showNumber, numRows, seatsPerRow, cancellationWindow);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please use the format: 'Setup <Show Number> <Number of Rows> <Number of Seats per Row> <Cancellation Window in minutes>'");
                        }
                    } else {
                        System.out.println("Invalid input. Please use the format: 'Setup <Show Number> <Number of Rows> <Number of Seats per Row> <Cancellation Window in minutes>'");
                    }
                    break;
                case "view":
                    if (parts.length == 2) {
                        try {
                            int showNumberToView = Integer.parseInt(parts[1]);
                            admin.viewShow(showNumberToView);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please use the format: 'View <Show Number>'");
                        }
                    } else {
                        System.out.println("Invalid input. Please use the format: 'View <Show Number>'");
                    }
                    break;
                case "exit":
                    System.exit(0);
                default:
                    System.out.println("Invalid command.");
            }
        }
    }

    private static void buyerCmds(Scanner scanner, Buyer buyer) {
        System.out.println("Check availability, book, or cancel (avail/book/cancel): ");
    }
}