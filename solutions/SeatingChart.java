/*
 * Reference solution for exercise 8: Seating chart.
 * Instructions: exercise 8 on the Practice page.
 */
public class SeatingChart {
    private char[][] seats = new char[3][4];

    public SeatingChart() {
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                seats[row][col] = '-';
            }
        }
    }

    public boolean book(int row, int col) {
        if (row < 0 || row >= seats.length || col < 0 || col >= seats[row].length) {
            return false; // outside the chart
        }
        if (seats[row][col] == 'X') {
            return false; // already taken
        }
        seats[row][col] = 'X';
        return true;
    }

    public void printChart() {
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                System.out.print(seats[row][col]);
                if (col < seats[row].length - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public int countFree() {
        int free = 0;
        for (int row = 0; row < seats.length; row++) {
            for (int col = 0; col < seats[row].length; col++) {
                if (seats[row][col] == '-') {
                    free++;
                }
            }
        }
        return free;
    }

    public static void main(String[] args) {
        SeatingChart chart = new SeatingChart();
        System.out.println(chart.book(1, 2));
        System.out.println(chart.book(1, 2));
        System.out.println(chart.book(5, 0));
        chart.printChart();
        System.out.println("Free seats: " + chart.countFree());
    }
}
