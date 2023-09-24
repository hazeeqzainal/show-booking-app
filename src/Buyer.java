import java.util.ArrayList;
import java.util.List;

public class Buyer {
    public void checkSeatAvailability(Show show) {
        // Check if the show exists
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }

        System.out.println("Available seat numbers for Show " + show.getShowNumber() + ":");
        List<String> availableSeats = getAvailableSeats(show);

        if (availableSeats.isEmpty()) {
            System.out.println("No seats available.");
        } else {
            for (String seatLabel : availableSeats) {
                System.out.println(seatLabel);
            }
        }
    }

    public boolean bookSeats(Show show, String phone, List<String> seatLabelsToBook) {
        return false;
    }

    public boolean cancelBooking(List<Show> shows, String ticketNumber, String phone) {
        return false;
    }

    // Helper method to get a list of available seats for a show
    private List<String> getAvailableSeats(Show show) {
        List<String> allSeats = generateSeats(show);
        List<String> bookedSeats = getBookedSeats(show);

        // Remove booked seats from the list of all seats to get available seats
        allSeats.removeAll(bookedSeats);

        return allSeats;
    }

    // Helper method to generate all possible seat labels for a show
    private List<String> generateSeats(Show show) {
        // Implement logic to generate seat labels based on the number of rows and seats per row
        List<String> seats = new ArrayList<>();
        for (char row = 'A'; row < 'A' + show.getNumRows(); row++) {
            for (int seatNum = 1; seatNum <= show.getSeatsPerRow(); seatNum++) {
                seats.add(row + String.valueOf(seatNum));
            }
        }
        return seats;
    }

    // Helper method to get a list of booked seats for a show
    private List<String> getBookedSeats(Show show) {
        List<String> bookedSeats = new ArrayList<>();

        // Implement logic to retrieve booked seats for the show
        for (Ticket ticket : show.getTickets()) {
            bookedSeats.addAll(ticket.getSeatLabels());
        }

        return bookedSeats;
    }
}
