import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class Admin {

    private static Map<Integer, Show> shows = new HashMap<>();

    public void setupShow(int showNumber, int numRows, int seatsPerRow, int cancellationWindow) {
        if (shows.containsKey(showNumber)) {
            System.out.println("Show with this number already exists.");
        } else if (numRows > 26 || numRows <= 0 || seatsPerRow > 10 || seatsPerRow <= 0) {
            System.out.println("Invalid number of rows or seats per row.");
        } else {
            Show show = new Show(showNumber, numRows, seatsPerRow, cancellationWindow);
            shows.put(showNumber, show);
            System.out.println("Show setup successfully.");
        }
    }

    public void viewShow(int showNumber) {
        Show show = shows.get(showNumber);
        if (show != null) {
            System.out.println("Show Number: " + show.getShowNumber());
            System.out.println("Number of Rows: " + show.getNumRows());
            System.out.println("Number of Seats per Row: " + show.getSeatsPerRow());
            System.out.println("Cancellation Window (minutes): " + show.getCancellationWindow());

            List<Seat> availableSeats = show.getAvailableSeats();
            System.out.println("Available Seats:");
            for (Seat seat : availableSeats) {
                System.out.println("Seat Number: " + seat.getSeatNumber());
            }

            // Add more details as needed
        } else {
            System.out.println("Show not found.");
        }
    }
}
