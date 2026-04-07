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

// ---------------- INVENTORY ----------------
class Inventory {
    private Map<String, Integer> availability = new HashMap<>();

    void addRoom(String type, int count) {
        availability.put(type, count);
    }

    // synchronized → thread safe
    synchronized boolean bookRoom(String type) {

        int available = availability.getOrDefault(type, 0);

        if (available > 0) {
            availability.put(type, available - 1);
            return true;
        }
        return false;
    }

    int getAvailability(String type) {
        return availability.getOrDefault(type, 0);
    }
}

// ---------------- BOOKING THREAD ----------------
class BookingThread extends Thread {

    private Queue<Reservation> queue;
    private Inventory inventory;

    BookingThread(Queue<Reservation> queue, Inventory inventory) {
        this.queue = queue;
        this.inventory = inventory;
    }

    public void run() {

        while (true) {

            Reservation request;

            // synchronized queue access
            synchronized (queue) {
                if (queue.isEmpty()) {
                    break;
                }
                request = queue.poll();
            }

            // process booking
            boolean success = inventory.bookRoom(request.roomType);

            if (success) {
                System.out.println(Thread.currentThread().getName()
                        + " → Booking CONFIRMED for "
                        + request.guestName + " (" + request.roomType + ")");
            } else {
                System.out.println(Thread.currentThread().getName()
                        + " → Booking FAILED for "
                        + request.guestName + " (" + request.roomType + ")");
            }
        }
    }
}

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    public static void concurrentBookingSimulation() {

        System.out.println("\n=== Concurrent Booking Simulation (UC11) ===");

        // Shared inventory
        Inventory inventory = new Inventory();
        inventory.addRoom("Single", 2);

        // Shared queue
        Queue<Reservation> queue = new LinkedList<>();
        queue.add(new Reservation("Alice", "Single"));
        queue.add(new Reservation("Bob", "Single"));
        queue.add(new Reservation("Charlie", "Single"));
        queue.add(new Reservation("David", "Single"));

        // Multiple threads (simulate users)
        BookingThread t1 = new BookingThread(queue, inventory);
        BookingThread t2 = new BookingThread(queue, inventory);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread interrupted");
        }

        System.out.println("Final Availability: " +
                inventory.getAvailability("Single"));
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        concurrentBookingSimulation();
    }
}