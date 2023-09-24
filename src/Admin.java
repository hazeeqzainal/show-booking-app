import java.util.List;

public class Admin {
    public void setupShow(List<Show> shows, int showNumber, int numRows, int seatsPerRow, int cancellationWindow) {
        if (showNumber <= 0 || showNumber > 999) {
            System.out.println("Invalid show number. Show number must be between 1 and 999.");
            return;
        }

        if (numRows <= 0 || numRows > 26) {
            System.out.println("Invalid number of rows. Number of rows must be between 1 and 26.");
            return;
        }

        if (seatsPerRow <= 0 || seatsPerRow > 10) {
            System.out.println("Invalid number of seats per row. Number of seats per row must be between 1 and 10.");
            return;
        }

        if (cancellationWindow <= 0) {
            System.out.println("Invalid cancellation window. Cancellation window must be greater than 0 minutes.");
            return;
        }

        // Check if the show number already exists
        for (Show show : shows) {
            if (show.getShowNumber() == showNumber) {
                System.out.println("Show " + showNumber + " already exists.");
                return;
            }
        }

        // Create a new show with the provided parameters and add it to the list of shows
        Show newShow = new Show(showNumber, numRows, seatsPerRow, cancellationWindow);
        shows.add(newShow);
        System.out.println("Show " + showNumber + " has been set up.");
    }

    public void viewShow(Show show) {
        if (show != null) {
            System.out.println("Show Number: " + show.getShowNumber());
            System.out.println("Number of Rows: " + show.getNumRows());
            System.out.println("Seats per Row: " + show.getSeatsPerRow());
            System.out.println("Cancellation Window (minutes): " + show.getCancellationWindow());

            // Iterate through each ticket and display its details
            if (!show.getTickets().isEmpty()) {
                for (Ticket ticket : show.getTickets()) {
                    System.out.println("Ticket#: " + ticket.getTicketNumber() +
                            ", Buyer Phone#: " + ticket.getBuyerPhone() +
                            ", Seat Numbers allocated to the buyer: " +
                            String.join(", ", ticket.getSeatLabels()));
                }
            } else {
                System.out.println("No tickets have been purchased.");
            }
        } else {
            System.out.println("Show not found.");
        }
    }
}
