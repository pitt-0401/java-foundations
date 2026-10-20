import java.util.Scanner;

/*
 * Reference solution for exercise 3: Score collector.
 * Instructions: exercise 3 on the Practice page.
 */
public class ScoreCollector {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        int sum = 0;
        boolean done = false;

        while (!done) {
            System.out.print("Score (0-10, -1 to finish): ");
            if (!scanner.hasNextInt()) {
                scanner.next(); // throw the bad input away
                System.out.println("That is not a whole number.");
            } else {
                int score = scanner.nextInt();
                if (score == -1) {
                    done = true;
                } else if (score < 0 || score > 10) {
                    System.out.println("Scores must be between 0 and 10.");
                } else {
                    count++;
                    sum += score;
                }
            }
        }

        System.out.println("You entered " + count + " scores.");
        if (count > 0) {
            System.out.printf("Average: %.1f%n", (double) sum / count);
        }
    }
}
