/*
 * Reference solution for exercise 1: Tip calculator.
 * Instructions: exercise 1 on the Practice page.
 */
public class TipCalculator {

    public static double calculateTip(double bill, int percent) {
        return bill * percent / 100.0;
    }

    public static void printReceipt(double bill, int percent) {
        double tip = calculateTip(bill, percent);
        double total = bill + tip;
        System.out.printf("Bill: %.2f%n", bill);
        System.out.printf("Tip (%d%%): %.2f%n", percent, tip);
        System.out.printf("Total: %.2f%n", total);
    }

    public static void main(String[] args) {
        printReceipt(42.50, 18);
        printReceipt(100.0, 20);
    }
}
