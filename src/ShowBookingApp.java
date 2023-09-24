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
                System.out.println("Invalid role. Please enter 'admin' or 'buyer'.");
            }
        }
    }

    private static void adminCmds(Scanner scanner, Admin admin) {
        System.out.println("ADMIN");
    }

    private static void buyerCmds(Scanner scanner, Buyer buyer) {
        System.out.println("BUYER");
    }
}