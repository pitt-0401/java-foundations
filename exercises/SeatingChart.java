/*
 * Exercise 8: Seating chart.
 * Instructions: exercise 8 on the Practice page.
 *
 * Fill the chart in the constructor, then write book, printChart and countFree.
 * Test: mvn test -Dtest=SeatingChartTest
 */
public class SeatingChart {
    private char[][] seats = new char[3][4];

    public SeatingChart() {
        // TODO: fill every seat with '-' using nested loops
    }

    public boolean book(int row, int col) {
        // TODO: false if outside the chart or already 'X'; otherwise set 'X' and return true
        return false;
    }

    public void printChart() {
        // TODO: print the chart row by row, seats separated by a space
    }

    public int countFree() {
        // TODO: count the '-' seats
        return 0;
    }

    public static void main(String[] args) {
        SeatingChart chart = new SeatingChart();
        // TODO: book(1, 2) twice and book(5, 0) once, printing each result,
        //       then print the chart and the number of free seats
    }
}
