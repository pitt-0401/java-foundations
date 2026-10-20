/*
 * Exercises 4 and 5: Pet shelter, parts 1 and 2.
 * Instructions: exercises 4 and 5 on the Practice page.
 *
 * Part 1: fields, constructors, getters, setAge, feed and play.
 * Part 2: totalPets, getTotalPets and toString.
 * Test: mvn test -Dtest=PetShelterPart1Test (then PetShelterPart2Test)
 */
public class Pet {
    // TODO part 1: private fields name, age and hunger (0 = full, 10 = starving)
    // TODO part 2: private static int totalPets

    public Pet() {
        // TODO part 1: name "Unknown", age 0, hunger 5
        // TODO part 2: replace with this("Unknown", 0);
    }

    public Pet(String name, int age) {
        // TODO part 1: set name and age, hunger 5
        // TODO part 2: use this.name and this.age, and count the pet
    }

    public String getName() {
        // TODO part 1
        return null;
    }

    public int getAge() {
        // TODO part 1
        return 0;
    }

    public int getHunger() {
        // TODO part 1
        return 0;
    }

    public void setAge(int age) {
        // TODO part 1: ignore negative ages
    }

    public void feed() {
        // TODO part 1: hunger goes down by 3, never below 0
    }

    public void play() {
        // TODO part 1: hunger goes up by 2, never above 10
    }

    public static int getTotalPets() {
        // TODO part 2
        return 0;
    }

    @Override
    public String toString() {
        // TODO part 2: return text like Pet [name: Rex, age: 3, hunger: 5]
        return "";
    }
}
