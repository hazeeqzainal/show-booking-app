import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Buyer {
    private AtomicInteger ticketCounter = new AtomicInteger(1);

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
                System.out.print(seatLabel.toUpperCase() + " ");
            }
        }
    }

    public void bookSeats(Show show, String phone, String seatLabelsToBookParam) {

        // Check if the show exists
        if (show == null) {
            System.out.println("Show not found.");
            return;
        }

        List<String> seatLabelsToBook = getSeatsToBook(seatLabelsToBookParam);

        if (seatLabelsToBook == null) {
            System.out.println("Please enter your seats again..");
            return;
        }

        // Check if the provided seat labels are valid and available
        for (String seatLabel : seatLabelsToBook) {
            if (!isValidSeatLabel(seatLabel)) {
                System.out.println("Invalid seat label format: " + seatLabel);
                return;
            }

            if (!isSeatAvailable(show, seatLabel)) {
                System.out.println("Seat " + seatLabel + " is not available.");
                return;
            }
        }

        // Check if the phone number has already booked a ticket for this show
        if (hasExistingBooking(show, phone)) {
            System.out.println("You have already booked a ticket for this show.");
        } else {
            // Generate a unique ticket number
            String ticketNumber = generateTicketNumber();

            // Create a new ticket with the provided seat labels
            Ticket ticket = new Ticket(ticketNumber, show.getShowNumber(), phone, seatLabelsToBook);

            // Add the ticket to the show's list of tickets
            show.addTicket(ticket);

            // Display the ticket details
            System.out.println("Ticket booked successfully.");
            System.out.println("Ticket Number: " + ticketNumber);
            System.out.println("Buyer Phone: " + phone);
            System.out.println("Seat Numbers: " + String.join(", ", seatLabelsToBook).toUpperCase());
        }
    }

    public void cancelBooking(List<Show> shows, String ticketNumber, String phone) {
        for (Show show : shows) {
            Map<String, Boolean> bookedSeats = show.getSeatAvailability();
            Ticket ticketToFind = null;
            for (Ticket ticket : show.getTickets()) {
                if (ticket.getTicketNumber().equals(ticketNumber)) {
                    ticketToFind = ticket;
                }
            }
            if (show.getShowNumber() == ticketToFind.getShowNumber()) {
                // Check if the ticket belongs to the provided phone number
                if (!isTicketBelongsToPhone(show, ticketNumber, phone)) {
                    continue; // Ticket does not match the provided phone number, continue to the next show
                }

                // Check if the cancellation window is still open (within 2 minutes)
                if (!isCancellationWindowOpen(show, ticketNumber)) {
                    continue; // Cancellation window has expired, continue to the next show
                }

                // Cancel the booking by marking the associated seats as available
                List<String> seatLabels = new ArrayList<>();
                for (Ticket ticket : show.getTickets()) {
                    if (ticket.getTicketNumber() == ticketNumber) {
                        seatLabels = ticket.getSeatLabels();
                    }
                }
                for (String seatLabel : seatLabels) {
                    bookedSeats.put(seatLabel, false); // Mark the seat as available
                }
                System.out.println("Your cancellation is successful.");
                return; // Cancellation successful
            }
        }
        System.out.println("Ticket not found.");
        return; // Ticket not found in any of the shows
    }

    // Helper methods
    private boolean isTicketBelongsToPhone(Show show, String ticketNumber, String phone) {
        for (Ticket ticket : show.getTickets()) {
            if (ticket.getTicketNumber().equals(ticketNumber) && ticket.getBuyerPhone().equals(phone)) {
                return true; // Ticket belongs to the provided phone number
            }
        }
        return false; // Ticket does not belong to the provided phone number
    }

    private boolean isCancellationWindowOpen(Show show, String ticketNumber) {
        for (Ticket ticket : show.getTickets()) {
            if (ticket.getTicketNumber().equals(ticketNumber)) {
                // Get the timestamp when the ticket was booked
                long bookingTimestamp = ticket.getBookingTimestamp();

                // Calculate the current time in milliseconds
                long currentTimeMillis = System.currentTimeMillis();

                // Calculate the difference in minutes
                long minutesDifference = (currentTimeMillis - bookingTimestamp) / (1000 * 60);

                // Check if the difference is within the cancellation window
                return minutesDifference <= show.getCancellationWindow();
            }
        }
        return false; // Ticket not found
    }
    private List<String> getAvailableSeats(Show show) {
        List<String> allSeats = generateSeats(show);
        List<String> bookedSeats = getBookedSeats(show);

        // Remove booked seats from the list of all seats to get available seats
        allSeats.removeAll(bookedSeats);

        return allSeats;
    }

    private List<String> generateSeats(Show show) {
        List<String> seats = new ArrayList<>();
        for (char row = 'a'; row < 'a' + show.getNumRows(); row++) {
            for (int seatNum = 1; seatNum <= show.getSeatsPerRow(); seatNum++) {
                seats.add(row + String.valueOf(seatNum));
            }
        }
        return seats;
    }

    private List<String> getBookedSeats(Show show) {
        List<String> bookedSeats = new ArrayList<>();

        for (Ticket ticket : show.getTickets()) {
            bookedSeats.addAll(ticket.getSeatLabels());
        }

        return bookedSeats;
    }

    private static List<String> getSeatsToBook(String seatLabelsToBookParam) {

        // Check if there are at least 4 components (Book, Show Number, Phone#, Seats)
        if (seatLabelsToBookParam != null) {
            // Split the seats input by commas to get individual seat labels
            String[] seatLabelsArray = seatLabelsToBookParam.split(",");

            List<String> seatLabelsToBook = new ArrayList<>();
            for (String seatLabel : seatLabelsArray) {
                seatLabelsToBook.add(seatLabel.trim());
            }

            return seatLabelsToBook;
        } else {
            return null;
        }
    }

    private boolean isValidSeatLabel(String seatLabel) {
        // Check if seatLabel matches the pattern of a single uppercase letter followed by a positive integer
        return seatLabel.matches("^[A-Za-z]\\d+$");
    }

    private boolean isSeatAvailable(Show show, String seatLabel) {
        // Get the set of booked seat labels for the show
        Set<String> bookedSeats = getBookedSeatLabels(show);

        // Check if the seatLabel is not in the set of booked seats
        return !bookedSeats.contains(seatLabel);
    }

    private Set<String> getBookedSeatLabels(Show show) {
        Set<String> bookedSeats = new HashSet<>();

        // Iterate through the list of tickets for the show and collect all booked seat labels
        for (Ticket ticket : show.getTickets()) {
            bookedSeats.addAll(ticket.getSeatLabels());
        }

        return bookedSeats;
    }

    private boolean hasExistingBooking(Show show, String phone) {
        // Iterate through the list of tickets for the show
        for (Ticket ticket : show.getTickets()) {
            // Check if the phone number matches the phone number on any existing ticket
            if (ticket.getBuyerPhone().equals(phone)) {
                return true; // Phone number already has a booking
            }
        }
        return false; // Phone number does not have an existing booking
    }

    private String generateTicketNumber() {
        // Get the current timestamp
        long timestamp = System.currentTimeMillis();

        // Format the timestamp as a string
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedTimestamp = dateFormat.format(new Date(timestamp));

        // Generate a unique ticket number by combining the timestamp and a unique counter
        int ticketNumber = ticketCounter.getAndIncrement();
        String uniqueTicketNumber = formattedTimestamp + "-" + ticketNumber;

        return uniqueTicketNumber;
    }
}
