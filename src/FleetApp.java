import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FleetApp {

    private final List<Vehicle> vehicles = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        boolean running = true;

        while (running) {
            printMenu();
            int choice = readInt("Enter your choice: ");
            System.out.println();

            switch (choice) {
                case 1 -> printAllVehicles();
                case 2 -> addNewCar();
                case 3 -> addNewBus();
                case 4 -> showTotalInsurance();
                case 5 -> showVehiclesOlderThan();
                case 6 -> performServiceForAll();
                case 7 -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }

            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("=== Fleet Management System ===");
        System.out.println("1. Print all vehicles");
        System.out.println("2. Add new car");
        System.out.println("3. Add new bus");
        System.out.println("4. Show total yearly insurance fees");
        System.out.println("5. Show vehicles older than N years");
        System.out.println("6. Perform service for all vehicles");
        System.out.println("7. Quit");
    }

    private void printAllVehicles() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        System.out.println("Vehicles in fleet:");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void addNewCar() {
        System.out.println("Adding new car:");
        String model = readNonEmptyString("Model: ");
        int year = readInt("Year: ");
        double basePrice = readDouble("Base price: ");
        int doors = readInt("Number of doors: ");

        try {
            Car car = new Car(model, year, basePrice, doors);
            vehicles.add(car);
            System.out.println("Car added: " + car);
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not add car: " + ex.getMessage());
        }
    }

    private void addNewBus() {
        System.out.println("Adding new bus:");
        String model = readNonEmptyString("Model: ");
        int year = readInt("Year: ");
        double basePrice = readDouble("Base price: ");
        int capacity = readInt("Passenger capacity: ");

        try {
            Bus bus = new Bus(model, year, basePrice, capacity);
            vehicles.add(bus);
            System.out.println("Bus added: " + bus);
        } catch (IllegalArgumentException ex) {
            System.out.println("Could not add bus: " + ex.getMessage());
        }
    }

    private void showTotalInsurance() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        double total = 0.0;
        for (Vehicle v : vehicles) {
            total += v.calculateInsuranceFee();   // polymorphism روی Vehicle
        }
        System.out.println("Total yearly insurance fees: " + total);
    }

    private void showVehiclesOlderThan() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        int currentYear = readInt("Current year: ");
        int n = readInt("Show vehicles older than N years, N = ");

        boolean any = false;
        for (Vehicle v : vehicles) {
            if (v.getAge(currentYear) > n) {
                System.out.println(v);
                any = true;
            }
        }
        if (!any) {
            System.out.println("No vehicles older than " + n + " years.");
        }
    }

    private void performServiceForAll() {
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles in the fleet.");
            return;
        }
        System.out.println("Performing service for all servicable vehicles:\n");
        for (Vehicle v : vehicles) {
            if (v instanceof Servicable servicable) {
                // polymorphism – بسته به نوع واقعی (Car/Bus) متد درست صدا زده می‌شود
                servicable.performService();
                System.out.println();
            }
        }
    }

    // ======= متدهای کمکی برای ورودی امن =======

    private String readNonEmptyString(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            if (line != null && !line.trim().isEmpty()) {
                return line.trim();
            }
            System.out.println("Please enter a non-empty value.");
        }
    }

    private int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine();
            try {
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}

