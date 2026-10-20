import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/* Tests for exercise 2: Leap year checker. Run: mvn test -Dtest=LeapYearTest */
class LeapYearTest {

    @Test
    void isLeapYearFollowsTheRule() {
        assertTrue(LeapYear.isLeapYear(2024), "isLeapYear(2024) should be true: divisible by 4");
        assertFalse(LeapYear.isLeapYear(2023), "isLeapYear(2023) should be false: not divisible by 4");
        assertFalse(LeapYear.isLeapYear(1900), "isLeapYear(1900) should be false: divisible by 100 but not by 400");
        assertTrue(LeapYear.isLeapYear(2000), "isLeapYear(2000) should be true: divisible by 400");
        assertFalse(LeapYear.isLeapYear(2100), "isLeapYear(2100) should be false: divisible by 100 but not by 400");
        assertTrue(LeapYear.isLeapYear(1996), "isLeapYear(1996) should be true: divisible by 4");
    }

    @Test
    void isLeapYearOneLineFollowsTheRule() {
        assertTrue(LeapYear.isLeapYearOneLine(2024), "isLeapYearOneLine(2024) should be true: divisible by 4");
        assertFalse(LeapYear.isLeapYearOneLine(2023), "isLeapYearOneLine(2023) should be false: not divisible by 4");
        assertFalse(LeapYear.isLeapYearOneLine(1900), "isLeapYearOneLine(1900) should be false: divisible by 100 but not by 400");
        assertTrue(LeapYear.isLeapYearOneLine(2000), "isLeapYearOneLine(2000) should be true: divisible by 400");
        assertFalse(LeapYear.isLeapYearOneLine(2100), "isLeapYearOneLine(2100) should be false: divisible by 100 but not by 400");
        assertTrue(LeapYear.isLeapYearOneLine(1996), "isLeapYearOneLine(1996) should be true: divisible by 4");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = ConsoleCapture.run(() -> LeapYear.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "2024: true",
                "2023: false",
                "1900: false",
                "2000: true"),
                output,
                "main should print the expected output from the Practice page");
    }
}
