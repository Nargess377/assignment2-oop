public class Bus extends Vehicle {

    private int passengerCapacity;

    public Bus(String model, int year, double basePrice, int passengerCapacity) {
        super(model, year, basePrice);
        setPassengerCapacity(passengerCapacity);
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        if (passengerCapacity <= 0) {
            throw new IllegalArgumentException("Passenger capacity must be > 0");
        }
        this.passengerCapacity = passengerCapacity;
    }

    @Override
    public double calculateInsuranceFee() {
        int currentYear = java.time.Year.now().getValue();
        int age = getAge(currentYear);
        return getBasePrice() * 0.03 + age * 25;
    }

    @Override
    public int getServiceIntervalKm() {
        // مثلا هر ۳۰٬۰۰۰ کیلومتر سرویس
        return 30000;
    }

    @Override
    public void performService() {
        System.out.println("Servicing BUS id=" + getId() + " (" + getModel() + ")");
        System.out.println("- Engine check");
        System.out.println("- Brake system check");
        System.out.println("- Passenger safety systems check");
        System.out.println("Next service in " + getServiceIntervalKm() + " km.");
    }

    @Override
    public String toString() {
        return "Bus{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", basePrice=" + basePrice +
                ", passengerCapacity=" + passengerCapacity +
                '}';
    }
}


