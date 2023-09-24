import java.util.ArrayList;
import java.util.List;

public class Show {
    private final int showNumber;
    private final int numRows;
    private final int seatsPerRow;
    private final int cancellationWindow;
    private final List<Seat> availableSeats;

    public Show(int showNumber, int numRows, int seatsPerRow, int cancellationWindow) {
        this.showNumber = showNumber;
        this.numRows = numRows;
        this.seatsPerRow = seatsPerRow;
        this.cancellationWindow = cancellationWindow;
        this.availableSeats = generateSeats();
    }

    private List<Seat> generateSeats() {
        List<Seat> seats = new ArrayList<>();
        for (int row = 1; row <= numRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                String seatLabel = generateSeatLabel(row, seatNum);
                seats.add(new Seat(seatLabel));
            }
        }
        return seats;
    }

    private String generateSeatLabel(int row, int seatNum) {
        char rowLabel = (char) ('A' + row - 1);
        return String.valueOf(rowLabel) + seatNum;
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

    public List<Seat> getAvailableSeats() {
        return availableSeats;
    }
}