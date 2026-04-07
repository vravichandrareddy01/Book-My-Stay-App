import java.util.HashMap;
import java.util.Map;

/**
 * bookmystayapp
 *
 * This class demonstrates centralized room inventory management
 * using a HashMap as a single source of truth.
 *
 * @author YourName
 * @version 3.1
 */

// Inventory Class
class RoomInventory {

    private Map<String, Integer> inventory;

    // Constructor
    public RoomInventory() {
        inventory = new HashMap<>();

        // Initialize room availability
        inventory.put("Single Room", 10);
        inventory.put("Double Room", 5);
        inventory.put("Suite Room", 2);
    }

    // Get availability
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    // Update availability
    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, current + change);
    }

    // Display inventory
    public void displayInventory() {
        System.out.println("---- Current Room Inventory ----\n");

        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println("Room Type: " + entry.getKey());
            System.out.println("Available: " + entry.getValue());
            System.out.println();
        }
    }
}

// Main Class
public class bookmystayapp {

    public static void main(String[] args) {

        System.out.println("=====================================");
        System.out.println("     Book My Stay App - v3.1         ");
        System.out.println("=====================================\n");

        // Create inventory object
        RoomInventory inventory = new RoomInventory();

        // Display initial inventory
        inventory.displayInventory();

        // Simulate updates
        System.out.println("Updating inventory...\n");

        inventory.updateAvailability("Single Room", -2);
        inventory.updateAvailability("Suite Room", 1);

        // Display updated inventory
        inventory.displayInventory();

        System.out.println("=====================================");
        System.out.println("     End of Inventory Display        ");
        System.out.println("=====================================");
    }
}