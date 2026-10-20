import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/* Tests for exercise 8: Seating chart. Run: mvn test -Dtest=SeatingChartTest */
class SeatingChartTest {

    @Test
    void newChartIsEmpty() {
        SeatingChart chart = new SeatingChart();
        assertEquals(12, chart.countFree(), "A new 3 x 4 chart should have 12 free seats");
        String output = ConsoleCapture.run(chart::printChart);
        assertEquals(ConsoleCapture.lines(
                "- - - -",
                "- - - -",
                "- - - -"),
                output,
                "printChart() on a new chart should print 3 rows of 4 '-' separated by spaces");
    }

    @Test
    void bookTakesAFreeSeat() {
        SeatingChart chart = new SeatingChart();
        assertTrue(chart.book(1, 2), "book(1, 2) on a free seat should return true");
        assertEquals(11, chart.countFree(), "After one booking, 11 seats should be free");
        assertFalse(chart.book(1, 2), "book(1, 2) a second time should return false: the seat is taken");
        assertEquals(11, chart.countFree(), "A failed booking should not change the number of free seats");
    }

    @Test
    void bookOutsideTheChartReturnsFalse() {
        SeatingChart chart = new SeatingChart();
        assertFalse(chart.book(5, 0), "book(5, 0) should return false: there is no row 5");
        assertFalse(chart.book(0, 4), "book(0, 4) should return false: columns go from 0 to 3");
        assertFalse(chart.book(-1, 0), "book(-1, 0) should return false: there is no row -1");
        assertFalse(chart.book(0, -1), "book(0, -1) should return false: there is no column -1");
        assertTrue(chart.book(2, 3), "book(2, 3) should return true: it is the last seat in the chart");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = ConsoleCapture.run(() -> SeatingChart.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "true",
                "false",
                "false",
                "- - - -",
                "- - X -",
                "- - - -",
                "Free seats: 11"),
                output,
                "main should print the expected output from the Practice page");
    }
}
