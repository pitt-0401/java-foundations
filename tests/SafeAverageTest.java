import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/* Tests for exercise 6: Safe average. Run: mvn test -Dtest=SafeAverageTest */
class SafeAverageTest {

    @Test
    void printsTheIntAverage() {
        String output = ConsoleCapture.run(() -> SafeAverage.printAverage(new int[] {4, 8, 6}));
        assertEquals("Average: 6", output, "printAverage({4, 8, 6}) should print \"Average: 6\"");
        output = ConsoleCapture.run(() -> SafeAverage.printAverage(new int[] {1, 2}));
        assertEquals("Average: 1", output,
                "printAverage({1, 2}) should print \"Average: 1\" (int division: 3 / 2 is 1)");
    }

    @Test
    void emptyArrayDoesNotCrash() {
        String output = ConsoleCapture.run(() -> SafeAverage.printAverage(new int[0]));
        assertEquals("No values to average.", output,
                "printAverage(new int[0]) should catch the ArithmeticException and print \"No values to average.\"");
    }

    @Test
    void nullDoesNotCrash() {
        String output = ConsoleCapture.run(() -> SafeAverage.printAverage(null));
        assertEquals("No array given.", output,
                "printAverage(null) should check for null first and print \"No array given.\"");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = ConsoleCapture.run(() -> SafeAverage.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "Average: 6",
                "No values to average.",
                "No array given."),
                output,
                "main should print the expected output from the Practice page");
    }
}
