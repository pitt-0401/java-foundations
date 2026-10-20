import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/*
 * Tests for exercise 3: Score collector. Run: mvn test -Dtest=ScoreCollectorTest
 *
 * The tests type the input for you. Because typed input is not part of the
 * printed output, the tests count the prompts and then check the other lines.
 */
// A test that runs longer than 5 seconds fails: the loop probably never ends.
@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class ScoreCollectorTest {

    private static final String PROMPT = "Score (0-10, -1 to finish):";

    private static String times(int n) {
        return n == 1 ? "1 time" : n + " times";
    }

    /** Runs main with the given input. Checks the number of prompts, then returns the other lines. */
    private static String runWithInput(String input, int expectedPrompts) {
        String output = ConsoleCapture.run(input, () -> ScoreCollector.main(new String[0]));
        int prompts = output.split(java.util.regex.Pattern.quote(PROMPT), -1).length - 1;
        assertEquals(expectedPrompts, prompts,
                "With the input " + input.replace("\n", " ").strip() + ", the prompt \"" + PROMPT
                        + "\" should appear " + times(expectedPrompts) + ", but it appeared "
                        + times(prompts) + ". Your program printed:\n" + output);
        StringBuilder rest = new StringBuilder();
        for (String line : output.replace(PROMPT, "\n").split("\n")) {
            if (!line.isBlank()) {
                rest.append(line.strip()).append('\n');
            }
        }
        return rest.toString().strip();
    }

    @Test
    void validScoresAreCountedAndAveraged() {
        String output = runWithInput("8\n7\n-1\n", 3);
        assertEquals(ConsoleCapture.lines(
                "You entered 2 scores.",
                "Average: 7.5"),
                output,
                "After the scores 8 and 7, the program should print the count and the average with 1 decimal");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = runWithInput("8\nten\n12\n7\n-1\n", 5);
        assertEquals(ConsoleCapture.lines(
                "That is not a whole number.",
                "Scores must be between 0 and 10.",
                "You entered 2 scores.",
                "Average: 7.5"),
                output,
                "With the input 8, ten, 12, 7, -1 the program should print the expected output from the Practice page");
    }

    @Test
    void noScoresMeansNoAverage() {
        String output = runWithInput("-1\n", 1);
        assertEquals("You entered 0 scores.", output,
                "When the first input is -1, print \"You entered 0 scores.\" and no average (do not divide by 0)");
    }

    @Test
    void averageIsRoundedToOneDecimal() {
        String output = runWithInput("10\n0\n0\n-1\n", 4);
        assertEquals(ConsoleCapture.lines(
                "You entered 3 scores.",
                "Average: 3.3"),
                output,
                "The scores 10, 0 and 0 count (0 and 10 are allowed) and their average is 3.3 with 1 decimal");
    }
}
