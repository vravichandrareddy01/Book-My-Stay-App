import java.util.*;

// ---------------- RESERVATION ----------------
class Reservation {
    String reservationId;
    String roomType;

    Reservation(String reservationId, String roomType) {
        this.reservationId = reservationId;
        this.roomType = roomType;
    }
}

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

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    // ---------------- UC10: CANCELLATION ----------------
    public static void cancelBooking() {

        System.out.println("\n=== Booking Cancellation & Rollback (UC10) ===");

        // Inventory setup
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 1); // after booking
        inventory.addRoom("Suite", 0);

        // Booking history (confirmed bookings)
        Map<String, Reservation> bookings = new HashMap<>();
        bookings.put("R1", new Reservation("R1", "Single"));
        bookings.put("R2", new Reservation("R2", "Suite"));

        // Stack for rollback (LIFO)
        Stack<String> rollbackStack = new Stack<>();

        // Cancellation request
        String cancelId = "R2"; // change to test

        // Validate booking exists
        if (!bookings.containsKey(cancelId)) {
            System.out.println("Cancellation FAILED: Booking not found");
            return;
        }

        // Get reservation
        Reservation res = bookings.get(cancelId);

        // Push to rollback stack
        rollbackStack.push(res.roomType);

        // Restore inventory (increment count)
        inventory.addRoom(res.roomType,
                inventory.getAvailability(res.roomType) + 1);

        // Remove from booking history
        bookings.remove(cancelId);

        System.out.println("Booking CANCELLED for ID: " + cancelId);

        // Show rollback info
        System.out.println("Rollback Stack (LIFO): " + rollbackStack);

        // Show updated inventory
        System.out.println("Updated Availability for " + res.roomType +
                ": " + inventory.getAvailability(res.roomType));
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        cancelBooking();
    }
}