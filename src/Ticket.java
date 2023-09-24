import java.util.List;

public class Ticket {
    private String ticketNumber;
    private String buyerPhone;
    private List<String> seatLabels;

    public Ticket(String ticketNumber, String buyerPhone, List<String> seatLabels) {
        this.ticketNumber = ticketNumber;
        this.buyerPhone = buyerPhone;
        this.seatLabels = seatLabels;
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
}