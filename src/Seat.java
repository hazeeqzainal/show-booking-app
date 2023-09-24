public class Seat {
    private final String seatNumber;
    private String ticketNumber;
    private String buyerPhone;
    private long bookingTime;

    private boolean booked = false;

    public Seat(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isBooked() {
        return booked;
    }

    public void book(String ticketNumber, String buyerPhone) {
        this.ticketNumber = ticketNumber;
        this.buyerPhone = buyerPhone;
        this.bookingTime = System.currentTimeMillis();
        this.booked = true;
    }

    public void cancelBooking() {
        this.ticketNumber = null;
        this.buyerPhone = null;
        this.bookingTime = 0;
        this.booked = false;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

}