public abstract class Vehicle implements Servicable {

    protected int id;
    protected static int idGen = 1;

    protected String model;
    protected int year;
    protected double basePrice;

    public Vehicle(String model, int year, double basePrice) {
        this.id = idGen++;
        setModel(model);
        setYear(year);
        setBasePrice(basePrice);
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        if (model == null || model.trim().isEmpty()) {
            throw new IllegalArgumentException("Model must not be null or empty");
        }
        this.model = model.trim();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        // اولین ماشین ۱۸۸۶ بوده، همینو می‌گیریم به عنوان حد پایین
        if (year < 1886 || year > 2100) {
            throw new IllegalArgumentException("Year is out of reasonable range");
        }
        this.year = year;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        if (basePrice <= 0) {
            throw new IllegalArgumentException("Base price must be positive");
        }
        this.basePrice = basePrice;
    }

    public int getAge(int currentYear) {
        int age = currentYear - year;
        return Math.max(age, 0);
    }

    // متد انتزاعی – زیرکلاس‌ها پیاده‌سازی می‌کنند
    public abstract double calculateInsuranceFee();

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", basePrice=" + basePrice +
                '}';
    }

    // متدهای Servicable اینجا پیاده‌سازی نمی‌شن، توی Car و Bus پیاده می‌کنیم.
}


