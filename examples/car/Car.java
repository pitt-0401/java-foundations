// A Car object keeps its own model, year and speed.
// The static field totalCars is shared by all Car objects.
public class Car {
    // 1. fields
    private static int totalCars = 0; // class (static): one value for all cars
    private String model;             // instance: each car has its own
    private int year;
    private int speed;

    // 2. constructors
    // no-arg: runs for new Car()
    public Car() {
        model = "Unknown";
        totalCars++;
    }

    // parameterized: runs for new Car("Roadster", 2024)
    public Car(String model, int year) {
        this.model = model; // this.model is the field, model is the parameter
        this.year = year;
        totalCars++;
    }

    // 3. methods
    public void drive() {
        System.out.println("Vroom");
    }

    public void accelerate(int amount) {     // amount: parameter
        int newSpeed = speed + amount;       // newSpeed: local variable
        speed = newSpeed;
    }

    // getters and setters
    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getSpeed() {
        return speed;
    }

    // a setter that checks: negative speeds are ignored
    public void setSpeed(int speed) {
        if (speed >= 0) {
            this.speed = speed;
        }
    }

    // static method: call it on the class, as Car.getTotalCars()
    public static int getTotalCars() {
        return totalCars;
    }

    // println(car) calls this method for you
    @Override
    public String toString() {
        return "Car [model: " + model + "]";
    }
}
