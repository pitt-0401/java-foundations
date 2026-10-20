import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/* Tests for exercise 5: Pet shelter, part 2. Run: mvn test -Dtest=PetShelterPart2Test */
class PetShelterPart2Test {

    @Test
    void toStringMatchesTheOutput() {
        assertEquals("Pet [name: Rex, age: 3, hunger: 5]", new Pet("Rex", 3).toString(),
                "new Pet(\"Rex\", 3).toString() should return \"Pet [name: Rex, age: 3, hunger: 5]\"");
        assertEquals("Pet [name: Unknown, age: 0, hunger: 5]", new Pet().toString(),
                "new Pet().toString() should return \"Pet [name: Unknown, age: 0, hunger: 5]\"");
    }

    @Test
    void everyConstructorCountsThePet() {
        // Other tests also create pets, so this test checks how much the total grows.
        int before = Pet.getTotalPets();
        new Pet("Rex", 3);
        assertEquals(before + 1, Pet.getTotalPets(),
                "Creating one pet with new Pet(\"Rex\", 3) should raise getTotalPets() by 1");
        new Pet();
        assertEquals(before + 2, Pet.getTotalPets(),
                "Creating one pet with new Pet() should raise getTotalPets() by 1, not 0 or 2");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        int before = Pet.getTotalPets();
        String output = ConsoleCapture.run(() -> PetShelter.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "Pet [name: Rex, age: 3, hunger: 5]",
                "Pet [name: Mia, age: 1, hunger: 5]",
                "Pet [name: Unknown, age: 0, hunger: 5]",
                "Total pets: " + (before + 3)),
                output,
                "main should create 3 pets, print each, then print the total. When main runs on its own,"
                        + " the total is 3 (here the test adds the " + before + " pets that other tests created).");
    }
}
