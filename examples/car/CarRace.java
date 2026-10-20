// Creates two Car objects and uses their methods.
// Run: javac Car.java CarRace.java, then java CarRace
public class CarRace {
    public static void main(String[] args) {
        Car car1 = new Car();                 // no-arg constructor
        Car car2 = new Car("Roadster", 2024); // parameterized constructor

        car2.drive();                         // Vroom
        car2.accelerate(50);
        car2.setSpeed(-10);                   // ignored: the setter checks
        int speed = car2.getSpeed();
        System.out.println(speed);            // 50

        // each object has its own field values
        System.out.println(car1.getSpeed());  // 0

        // println calls toString()
        System.out.println(car1);             // Car [model: Unknown]
        System.out.println(car2);             // Car [model: Roadster]

        // static method: call it on the class
        int n = Car.getTotalCars();
        System.out.println(n);                // 2
    }
}
