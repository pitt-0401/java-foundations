import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/* Tests for exercise 1: Tip calculator. Run: mvn test -Dtest=TipCalculatorTest */
class TipCalculatorTest {

    @Test
    void calculateTipReturnsTheTip() {
        assertEquals(7.65, TipCalculator.calculateTip(42.50, 18), 0.001,
                "calculateTip(42.50, 18) should return 7.65");
        assertEquals(20.0, TipCalculator.calculateTip(100.0, 20), 0.001,
                "calculateTip(100.0, 20) should return 20.0");
        assertEquals(1.5, TipCalculator.calculateTip(10.0, 15), 0.001,
                "calculateTip(10.0, 15) should return 1.5 (check for int division)");
    }

    @Test
    void printReceiptPrintsBillTipAndTotal() {
        String output = ConsoleCapture.run(() -> TipCalculator.printReceipt(42.50, 18));
        assertEquals(ConsoleCapture.lines(
                "Bill: 42.50",
                "Tip (18%): 7.65",
                "Total: 50.15"),
                output,
                "printReceipt(42.50, 18) should print three lines with 2 decimals each");
    }

    @Test
    void mainPrintsTheExpectedOutput() {
        String output = ConsoleCapture.run(() -> TipCalculator.main(new String[0]));
        assertEquals(ConsoleCapture.lines(
                "Bill: 42.50",
                "Tip (18%): 7.65",
                "Total: 50.15",
                "Bill: 100.00",
                "Tip (20%): 20.00",
                "Total: 120.00"),
                output,
                "main should print the expected output from the Practice page");
    }
}
