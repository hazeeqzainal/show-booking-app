import java.util.List;

public class Ticket {
    private String ticketNumber;
    private int showNumber;
    private String buyerPhone;
    private List<String> seatLabels;
    private long bookingTimestamp;

    public Ticket(String ticketNumber, int showNumber, String buyerPhone, List<String> seatLabels) {
        this.ticketNumber = ticketNumber;
        this.showNumber = showNumber;
        this.buyerPhone = buyerPhone;
        this.seatLabels = seatLabels;
        this.bookingTimestamp = System.currentTimeMillis();
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public String getBuyerPhone() {
        return buyerPhone;
    }

    public List<String> getSeatLabels() {
        return seatLabels;
    }

    public long getBookingTimestamp() {
        return bookingTimestamp;
    }

    public int getShowNumber() {
        return showNumber;
    }
}