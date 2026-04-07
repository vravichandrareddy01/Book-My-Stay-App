import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue
 *
 * This class demonstrates handling booking requests using a Queue
 * to ensure First-Come-First-Served (FIFO) processing.
 *
 * No inventory updates are performed in this stage.
 *
 * @author YourName
 * @version 5.1
 */

// Reservation class representing a booking request
class Reservation {
    private String guestName;
    private String roomType;

    // Constructor
    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    // Display reservation details
    public void display() {
        System.out.println("Guest Name: " + guestName);
        System.out.println("Requested Room: " + roomType);
        System.out.println();
    }
}

// Booking Queue Manager
class BookingRequestQueue {

    private Queue<Reservation> queue;

    // Constructor
    public BookingRequestQueue() {
        queue = new LinkedList<>();
    }

    // Add booking request
    public void addRequest(Reservation reservation) {
        queue.offer(reservation);
        System.out.println("Booking request added for: " + reservation);
    }

    // Display all requests in queue
    public void displayQueue() {
        System.out.println("\n---- Booking Request Queue (FIFO Order) ----\n");

        if (queue.isEmpty()) {
            System.out.println("No booking requests in queue.");
            return;
        }

        for (Reservation r : queue) {
            r.display();
        }
    }
}

// Main Application Class
public class UseCase5BookingRequestQueue {

    public static void main(String[] args) {

        System.out.println("=========================================");
        System.out.println("     Book My Stay App - v5.1             ");
        System.out.println("=========================================\n");

        // Initialize booking queue
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        // Simulate booking requests
        Reservation r1 = new Reservation("Alice", "Single Room");
        Reservation r2 = new Reservation("Bob", "Double Room");
        Reservation r3 = new Reservation("Charlie", "Suite Room");

        // Add requests to queue (FIFO)
        bookingQueue.addRequest(r1);
        bookingQueue.addRequest(r2);
        bookingQueue.addRequest(r3);

        // Display queue
        bookingQueue.displayQueue();

        System.out.println("=========================================");
        System.out.println("     Requests Waiting for Processing     ");
        System.out.println("=========================================");
    }
}