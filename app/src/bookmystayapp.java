import java.util.*;

// ---------------- SERVICE ----------------
class Service {
    String name;
    double cost;

    Service(String name, double cost) {
        this.name = name;
        this.cost = cost;
    }
}

// ---------------- MAIN CLASS ----------------
public class BookMyStayApp {

    // ---------------- UC7: ADD-ON SERVICE SELECTION ----------------
    public static void addOnServices() {

        System.out.println("\n=== Add-On Services (UC7) ===");

        // Map: ReservationID -> List of Services
        Map<String, List<Service>> serviceMap = new HashMap<>();

        // Create service lists
        List<Service> services1 = new ArrayList<>();
        services1.add(new Service("Breakfast", 200));
        services1.add(new Service("WiFi", 100));

        List<Service> services2 = new ArrayList<>();
        services2.add(new Service("Airport Pickup", 500));

        // Map services to reservations
        serviceMap.put("R1", services1);
        serviceMap.put("R2", services2);

        // Display services + calculate total cost
        for (String reservationId : serviceMap.keySet()) {

            System.out.println("Reservation ID: " + reservationId);

            double totalCost = 0;

            for (Service s : serviceMap.get(reservationId)) {
                System.out.println("Service: " + s.name + " | Cost: ₹" + s.cost);
                totalCost += s.cost;
            }

            System.out.println("Total Add-On Cost: ₹" + totalCost);
            System.out.println("----------------------");
        }
    }

    // ---------------- MAIN ----------------
    public static void main(String[] args) {
        addOnServices();
    }
}