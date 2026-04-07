import java.util.*;

// ---------------- INVENTORY ----------------
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    void addRoom(String type, int count) {
        availability.put(type, count);
    }

    int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }
}

// ---------------- RESERVATION ----------------
class Reservation {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    // ---------------- UC6: BOOKING PROCESSING ----------------
    public static void processBookingRequests() {

        System.out.println("\n=== Processing Bookings (UC6) ===");

        // Inventory setup
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Double", 0);
        inventory.addRoom("Suite", 3);

        // Booking Queue (FIFO)
        Queue<Reservation> bookingQueue = new LinkedList<>();

        bookingQueue.add(new Reservation("Alice", "Single"));
        bookingQueue.add(new Reservation("Bob", "Suite"));
        bookingQueue.add(new Reservation("Charlie", "Single"));
        bookingQueue.add(new Reservation("David", "Single")); // extra request

        // Process queue
        while (!bookingQueue.isEmpty()) {

            Reservation request = bookingQueue.poll();

            int available = inventory.getAvailability(request.roomType);

            if (available > 0) {

                // Reduce count → prevent double booking
                inventory.addRoom(request.roomType, available - 1);

                System.out.println("Booking CONFIRMED for "
                        + request.guestName + " (" + request.roomType + ")");
            } else {

                System.out.println("Booking FAILED for "
                        + request.guestName + " (" + request.roomType + " not available)");
            }
        }
    }

    // ---------------- MAIN METHOD ----------------
    public static void main(String[] args) {
        processBookingRequests();
    }
}