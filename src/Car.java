public class Car extends Vehicle {

    private int numberOfDoors;

    public Car(String model, int year, double basePrice, int numberOfDoors) {
        super(model, year, basePrice);
        setNumberOfDoors(numberOfDoors);
    }

    public int getNumberOfDoors() {
        return numberOfDoors;
    }

    public void setNumberOfDoors(int numberOfDoors) {
        if (numberOfDoors <= 0) {
            throw new IllegalArgumentException("Doors must be > 0");
        }
        this.numberOfDoors = numberOfDoors;
    }

    @Override
    public double calculateInsuranceFee() {
        int currentYear = java.time.Year.now().getValue();
        int age = getAge(currentYear);
        return getBasePrice() * 0.02 + age * 15;
    }

    @Override
    public int getServiceIntervalKm() {
        return 15000;
    }

    @Override
    public void performService() {
        System.out.println("Servicing CAR id=" + getId() + " (" + getModel() + ")");
        System.out.println("- Oil change");
        System.out.println("- Tire rotation");
        System.out.println("Next service in " + getServiceIntervalKm() + " km.");
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", basePrice=" + basePrice +
                ", numberOfDoors=" + numberOfDoors +
                '}';
    }
}


