import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/* Tests for exercise 9: Bubble sort. Run: mvn test -Dtest=BubbleSortTest */
// A test that runs longer than 5 seconds fails: the loop probably never ends.
@Timeout(value = 5, threadMode = Timeout.ThreadMode.SEPARATE_THREAD)
class BubbleSortTest {

    @Test
    void sortPrintsEveryPassThatSwapped() {
        int[] numbers = {9, 3, 6, 2, 7, 1, 4};
        String output = ConsoleCapture.run(() -> BubbleSort.sort(numbers));
        assertEquals(ConsoleCapture.lines(
                "Pass 1: [3, 6, 2, 7, 1, 4, 9]",
                "Pass 2: [3, 2, 6, 1, 4, 7, 9]",
                "Pass 3: [2, 3, 1, 4, 6, 7, 9]",
                "Pass 4: [2, 1, 3, 4, 6, 7, 9]",
                "Pass 5: [1, 2, 3, 4, 6, 7, 9]"),
                output,
                "sort({9, 3, 6, 2, 7, 1, 4}) should print the array after each of the 5 passes that swapped");
        assertArrayEquals(new int[] {1, 2, 3, 4, 6, 7, 9}, numbers, "After sort, the array should be in order");
    }

    @Test
    void sortWorksForOtherArrays() {
        int[] numbers = {5, 4, 3, 2, 1};
        ConsoleCapture.run(() -> BubbleSort.sort(numbers));
        assertArrayEquals(new int[] {1, 2, 3, 4, 5}, numbers, "sort({5, 4, 3, 2, 1}) should give {1, 2, 3, 4, 5}");
        int[] two = {2, 1};
        String output = ConsoleCapture.run(() -> BubbleSort.sort(two));
        assertEquals("Pass 1: [1, 2]", output, "sort({2, 1}) should print one pass: \"Pass 1: [1, 2]\"");
        int[] sorted = {1, 2, 3};
        output = ConsoleCapture.run(() -> BubbleSort.sort(sorted));
        assertEquals("", output, "sort({1, 2, 3}) makes no swaps, so it should print nothing");
        assertArrayEquals(new int[] {1, 2, 3}, sorted, "sort should not change an array that is already sorted");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = ConsoleCapture.run(() -> BubbleSort.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "Pass 1: [3, 6, 2, 7, 1, 4, 9]",
                "Pass 2: [3, 2, 6, 1, 4, 7, 9]",
                "Pass 3: [2, 3, 1, 4, 6, 7, 9]",
                "Pass 4: [2, 1, 3, 4, 6, 7, 9]",
                "Pass 5: [1, 2, 3, 4, 6, 7, 9]"),
                output,
                "main should print the expected output from the Practice page");
    }
}
