import java.util.Arrays;

/*
 * Reference solution for exercise 7: Score statistics.
 * Instructions: exercise 7 on the Practice page.
 */
public class ScoreStatistics {

    public static int sum(int[] a) {
        int total = 0;
        for (int value : a) {
            total += value;
        }
        return total;
    }

    public static double average(int[] a) {
        return (double) sum(a) / a.length;
    }

    public static int indexOfMax(int[] a) {
        int maxIndex = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] > a[maxIndex]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static int countAtLeast(int[] a, int limit) {
        int count = 0;
        for (int value : a) {
            if (value >= limit) {
                count++;
            }
        }
        return count;
    }

    public static int indexOf(int[] a, int target) {
        for (int i = 0; i < a.length; i++) {
            if (a[i] == target) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] scores = {72, 95, 88, 64, 100, 79, 88};
        System.out.println(Arrays.toString(scores));
        System.out.println("Sum: " + sum(scores));
        System.out.printf("Average: %.2f%n", average(scores));
        int maxIndex = indexOfMax(scores);
        System.out.println("Max: " + scores[maxIndex] + " at index " + maxIndex);
        System.out.println("Scores >= 80: " + countAtLeast(scores, 80));
        System.out.println("First 88 at index: " + indexOf(scores, 88));
    }
}
