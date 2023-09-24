import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Show {
    private int showNumber;
    private int numRows;
    private int seatsPerRow;
    private int cancellationWindow;
    private Map<String, Boolean> seatAvailability;
    private List<Ticket> tickets;

    public Show(int showNumber, int numRows, int seatsPerRow, int cancellationWindow) {
        this.showNumber = showNumber;
        this.numRows = numRows;
        this.seatsPerRow = seatsPerRow;
        this.cancellationWindow = cancellationWindow;
        this.seatAvailability = new HashMap<>();
        this.tickets = new ArrayList<>();
        initializeSeats();
    }

    private void initializeSeats() {
        for (int row = 1; row <= numRows; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                String seatLabel = generateSeatLabel(row, seat);
                seatAvailability.put(seatLabel, true); // Initialize all seats as available
            }
        }
    }

    private String generateSeatLabel(int row, int seat) {
        char rowChar = (char) ('A' + row - 1);
        return rowChar + String.valueOf(seat);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public int getShowNumber() {
        return showNumber;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public int getCancellationWindow() {
        return cancellationWindow;
    }

}