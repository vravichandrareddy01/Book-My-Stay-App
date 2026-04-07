import java.util.*;

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

    // ---------------- UC8: BOOKING HISTORY & REPORT ----------------
    public static void bookingHistoryReport() {

        System.out.println("\n=== Booking History & Report (UC8) ===");

        // List to store confirmed bookings (ordered)
        List<Reservation> bookingHistory = new ArrayList<>();

        // Simulating confirmed bookings
        bookingHistory.add(new Reservation("Alice", "Single"));
        bookingHistory.add(new Reservation("Bob", "Suite"));
        bookingHistory.add(new Reservation("Charlie", "Single"));

        // Display booking history
        System.out.println("\n--- Booking History ---");
        for (Reservation r : bookingHistory) {
            System.out.println("Guest: " + r.guestName + " | Room: " + r.roomType);
        }

        // Generate simple report
        System.out.println("\n--- Booking Summary Report ---");

        Map<String, Integer> report = new HashMap<>();

        for (Reservation r : bookingHistory) {
            report.put(r.roomType, report.getOrDefault(r.roomType, 0) + 1);
        }

        // Display report
        for (String roomType : report.keySet()) {
            System.out.println(roomType + " Bookings: " + report.get(roomType));
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        bookingHistoryReport();
    }
}