import java.util.*;

// ---------------- CUSTOM EXCEPTION ----------------
class InvalidBookingException extends Exception {
    InvalidBookingException(String message) {
        super(message);
    }
}

// ---------------- INVENTORY ----------------
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    void addRoom(String type, int count) {
        availability.put(type, count);
    }

    int getAvailability(String type) {
        return availability.getOrDefault(type, -1); // -1 = invalid room type
    }
}

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    // ---------------- VALIDATION METHOD ----------------
    public static void validateBooking(String roomType, int requestedRooms, Inventory inventory)
            throws InvalidBookingException {

        // Check valid room type
        int available = inventory.getAvailability(roomType);

        if (available == -1) {
            throw new InvalidBookingException("Invalid Room Type: " + roomType);
        }

        // Check valid quantity
        if (requestedRooms <= 0) {
            throw new InvalidBookingException("Invalid number of rooms requested");
        }

        // Check availability
        if (requestedRooms > available) {
            throw new InvalidBookingException("Not enough rooms available");
        }
    }

    // ---------------- UC9: ERROR HANDLING ----------------
    public static void processBookingWithValidation() {

        System.out.println("\n=== Error Handling & Validation (UC9) ===");

        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Suite", 1);

        // Test inputs
        String roomType = "Single";
        int requestedRooms = 3; // change to test errors

        try {
            // Validate first (fail-fast)
            validateBooking(roomType, requestedRooms, inventory);

            // If valid → process booking
            inventory.addRoom(roomType,
                    inventory.getAvailability(roomType) - requestedRooms);

            System.out.println("Booking SUCCESS for " + roomType);

        } catch (InvalidBookingException e) {

            // Graceful error handling
            System.out.println("Booking FAILED: " + e.getMessage());
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        processBookingWithValidation();
    }
}