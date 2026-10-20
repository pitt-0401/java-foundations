import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/* Tests for exercise 7: Score statistics. Run: mvn test -Dtest=ScoreStatisticsTest */
class ScoreStatisticsTest {

    private static final int[] SCORES = {72, 95, 88, 64, 100, 79, 88};

    @Test
    void sumAddsAllValues() {
        assertEquals(586, ScoreStatistics.sum(SCORES), "sum of {72, 95, 88, 64, 100, 79, 88} should be 586");
        assertEquals(0, ScoreStatistics.sum(new int[0]), "sum of an empty array should be 0");
    }

    @Test
    void averageIsADouble() {
        assertEquals(83.714, ScoreStatistics.average(SCORES), 0.001,
                "average of {72, 95, 88, 64, 100, 79, 88} should be about 83.714");
        assertEquals(1.5, ScoreStatistics.average(new int[] {1, 2}), 0.001,
                "average of {1, 2} should be 1.5, not 1.0 (cast to double before dividing)");
    }

    @Test
    void indexOfMaxFindsTheLargestValue() {
        assertEquals(4, ScoreStatistics.indexOfMax(SCORES), "The largest score, 100, is at index 4");
        assertEquals(0, ScoreStatistics.indexOfMax(new int[] {9, 1, 2}), "In {9, 1, 2} the largest value is at index 0");
        assertEquals(2, ScoreStatistics.indexOfMax(new int[] {-5, -3, -1}),
                "In {-5, -3, -1} the largest value is at index 2 (do not start the maximum at 0)");
    }

    @Test
    void countAtLeastIncludesTheLimit() {
        assertEquals(4, ScoreStatistics.countAtLeast(SCORES, 80), "4 scores are >= 80");
        assertEquals(2, ScoreStatistics.countAtLeast(SCORES, 95), "2 scores are >= 95 (95 itself counts)");
        assertEquals(0, ScoreStatistics.countAtLeast(SCORES, 101), "No score is >= 101");
    }

    @Test
    void indexOfFindsTheFirstPosition() {
        assertEquals(2, ScoreStatistics.indexOf(SCORES, 88), "The first 88 is at index 2");
        assertEquals(0, ScoreStatistics.indexOf(SCORES, 72), "72 is at index 0");
        assertEquals(-1, ScoreStatistics.indexOf(SCORES, 50), "50 is not in the array, so indexOf should return -1");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = ConsoleCapture.run(() -> ScoreStatistics.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "[72, 95, 88, 64, 100, 79, 88]",
                "Sum: 586",
                "Average: 83.71",
                "Max: 100 at index 4",
                "Scores >= 80: 4",
                "First 88 at index: 2"),
                output,
                "main should print the expected output from the Practice page");
    }
}
