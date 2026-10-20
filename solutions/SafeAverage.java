/*
 * Reference solution for exercise 6: Safe average.
 * Instructions: exercise 6 on the Practice page.
 */
public class SafeAverage {

    public static void printAverage(int[] values) {
        if (values == null) {
            System.out.println("No array given.");
            return;
        }
        int sum = 0;
        for (int value : values) {
            sum += value;
        }
        try {
            int average = sum / values.length;
            System.out.println("Average: " + average);
        } catch (ArithmeticException e) {
            System.out.println("No values to average.");
        }
    }

    public static void main(String[] args) {
        printAverage(new int[] {4, 8, 6});
        printAverage(new int[0]);
        printAverage(null);
    }
}
