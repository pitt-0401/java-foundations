import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/* Tests for exercise 4: Pet shelter, part 1. Run: mvn test -Dtest=PetShelterPart1Test */
class PetShelterPart1Test {

    @Test
    void noArgConstructorUsesDefaults() {
        Pet pet = new Pet();
        assertEquals("Unknown", pet.getName(), "new Pet() should have the name \"Unknown\"");
        assertEquals(0, pet.getAge(), "new Pet() should have age 0");
        assertEquals(5, pet.getHunger(), "new Pet() should have hunger 5");
    }

    @Test
    void secondConstructorSetsNameAndAge() {
        Pet rex = new Pet("Rex", 3);
        assertEquals("Rex", rex.getName(), "new Pet(\"Rex\", 3) should have the name \"Rex\"");
        assertEquals(3, rex.getAge(), "new Pet(\"Rex\", 3) should have age 3");
        assertEquals(5, rex.getHunger(), "new Pet(\"Rex\", 3) should have hunger 5");
    }

    @Test
    void setAgeIgnoresNegativeAges() {
        Pet pet = new Pet("Rex", 3);
        pet.setAge(4);
        assertEquals(4, pet.getAge(), "After setAge(4), getAge() should return 4");
        pet.setAge(-2);
        assertEquals(4, pet.getAge(), "setAge(-2) should be ignored, so getAge() should still return 4");
    }

    @Test
    void feedLowersHungerButNotBelowZero() {
        Pet rex = new Pet("Rex", 3);
        rex.feed();
        assertEquals(2, rex.getHunger(), "Hunger 5 after feed() should be 2");
        rex.feed();
        assertEquals(0, rex.getHunger(), "Hunger 2 after feed() should be 0, never below 0");
        rex.feed();
        assertEquals(0, rex.getHunger(), "Hunger 0 after feed() should stay 0");
    }

    @Test
    void playRaisesHungerButNotAboveTen() {
        Pet mia = new Pet("Mia", 1);
        mia.play();
        assertEquals(7, mia.getHunger(), "Hunger 5 after play() should be 7");
        mia.play();
        assertEquals(9, mia.getHunger(), "Hunger 7 after play() should be 9");
        mia.play();
        assertEquals(10, mia.getHunger(), "Hunger 9 after play() should be 10, never above 10");
        mia.play();
        assertEquals(10, mia.getHunger(), "Hunger 10 after play() should stay 10");
    }
}
