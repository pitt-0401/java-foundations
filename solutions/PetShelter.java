/*
 * Reference solution for exercises 4 and 5: Pet shelter, parts 1 and 2.
 * Instructions: exercises 4 and 5 on the Practice page.
 *
 * This is the finished main from part 2. In part 1, main printed Rex's and
 * Mia's hunger instead; part 2 replaces that test.
 */
public class PetShelter {

    public static void main(String[] args) {
        Pet rex = new Pet("Rex", 3);
        Pet mia = new Pet("Mia", 1);
        Pet unknown = new Pet();
        System.out.println(rex);
        System.out.println(mia);
        System.out.println(unknown);
        System.out.println("Total pets: " + Pet.getTotalPets());
    }
}
