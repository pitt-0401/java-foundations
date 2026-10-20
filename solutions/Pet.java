/*
 * Reference solution for exercises 4 and 5: Pet shelter, parts 1 and 2.
 * Instructions: exercises 4 and 5 on the Practice page.
 */
public class Pet {
    private static int totalPets = 0;

    private String name;
    private int age;
    private int hunger; // 0 = full, 10 = starving

    public Pet() {
        this("Unknown", 0);
    }

    public Pet(String name, int age) {
        this.name = name;
        this.age = age;
        this.hunger = 5;
        totalPets++;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getHunger() {
        return hunger;
    }

    public void setAge(int age) {
        if (age >= 0) {
            this.age = age;
        }
    }

    public void feed() {
        hunger = hunger - 3;
        if (hunger < 0) {
            hunger = 0;
        }
    }

    public void play() {
        hunger = hunger + 2;
        if (hunger > 10) {
            hunger = 10;
        }
    }

    public static int getTotalPets() {
        return totalPets;
    }

    @Override
    public String toString() {
        return "Pet [name: " + name + ", age: " + age + ", hunger: " + hunger + "]";
    }
}
