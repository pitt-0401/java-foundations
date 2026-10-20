import java.util.Arrays;

/*
 * Reference solution for exercise 9: Bubble sort.
 * Instructions: exercise 9 on the Practice page.
 */
public class BubbleSort {

    public static void sort(int[] numbers) {
        int n = numbers.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - i - 1; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) {
                return; // no swaps: the array is sorted
            }
            System.out.println("Pass " + (i + 1) + ": " + Arrays.toString(numbers));
        }
    }

    public static void main(String[] args) {
        int[] numbers = {9, 3, 6, 2, 7, 1, 4};
        sort(numbers);
    }
}
