import java.util.HashMap;
import java.util.Map;

/**
 * MAIN CLASS - UseCase4RoomSearch
 * Demonstrates Room Search & Availability Check (Read-Only)
 */
class UseCase4RoomSearch {

    public static void main(String[] args) {

        // Create room objects (Domain Model)
        Room singleRoom = new Room("Single", 1, 250, 1500.0);
        Room doubleRoom = new Room("Double", 2, 400, 2500.0);
        Room suiteRoom = new Room("Suite", 3, 750, 5000.0);

        // Setup inventory (State Holder)
        Map<String, Integer> roomData = new HashMap<>();
        roomData.put("Single", 5);
        roomData.put("Double", 3);
        roomData.put("Suite", 2);

        RoomInventory inventory = new RoomInventory(roomData);

        // Search Service (Read-only logic)
        RoomSearchService searchService = new RoomSearchService();
        searchService.searchAvailableRooms(inventory, singleRoom, doubleRoom, suiteRoom);
    }
}