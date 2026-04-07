import java.io.*;
import java.util.*;

// ---------------- RESERVATION ----------------
class Reservation implements Serializable {
    String guestName;
    String roomType;

    Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

// ---------------- INVENTORY ----------------
class Inventory implements Serializable {
    private Map<String, Integer> availability = new HashMap<>();

    void addRoom(String type, int count) {
        availability.put(type, count);
    }

    Map<String, Integer> getAllRooms() {
        return availability;
    }
}

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    static final String FILE_NAME = "data.ser";

    // ---------------- SAVE DATA ----------------
    public static void saveData(List<Reservation> bookings, Inventory inventory) {

        try {
            ObjectOutputStream out = new ObjectOutputStream(
                    new FileOutputStream(FILE_NAME));

            out.writeObject(bookings);
            out.writeObject(inventory);

            out.close();
            System.out.println("Data saved successfully");

        } catch (IOException e) {
            System.out.println("Error saving data");
        }
    }

    // ---------------- LOAD DATA ----------------
    public static void loadData() {

        try {
            ObjectInputStream in = new ObjectInputStream(
                    new FileInputStream(FILE_NAME));

            List<Reservation> bookings =
                    (List<Reservation>) in.readObject();

            Inventory inventory =
                    (Inventory) in.readObject();

            in.close();

            System.out.println("\n=== Recovered Data (UC12) ===");

            // Show bookings
            System.out.println("\nBookings:");
            for (Reservation r : bookings) {
                System.out.println(r.guestName + " - " + r.roomType);
            }

            // Show inventory
            System.out.println("\nInventory:");
            for (String type : inventory.getAllRooms().keySet()) {
                System.out.println(type + ": " +
                        inventory.getAllRooms().get(type));
            }

        } catch (Exception e) {
            System.out.println("No previous data found. Starting fresh.");
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {

        // Create sample data
        List<Reservation> bookings = new ArrayList<>();
        bookings.add(new Reservation("Alice", "Single"));
        bookings.add(new Reservation("Bob", "Suite"));

        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);
        inventory.addRoom("Suite", 1);

        // Save data
        saveData(bookings, inventory);

        // Load data (simulate restart)
        loadData();
    }
}