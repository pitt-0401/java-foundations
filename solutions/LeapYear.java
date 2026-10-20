/*
 * Reference solution for exercise 2: Leap year checker.
 * Instructions: exercise 2 on the Practice page.
 */
public class LeapYear {

    // First version: if / else if / else, most specific rule first.
    public static boolean isLeapYear(int year) {
        if (year % 400 == 0) {
            return true;
        } else if (year % 100 == 0) {
            return false;
        } else if (year % 4 == 0) {
            return true;
        } else {
            return false;
        }
    }

    // Second version: one return statement with && and ||.
    public static boolean isLeapYearOneLine(int year) {
        return (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
    }

    public static void main(String[] args) {
        System.out.println("2024: " + isLeapYear(2024));
        System.out.println("2023: " + isLeapYear(2023));
        System.out.println("1900: " + isLeapYear(1900));
        System.out.println("2000: " + isLeapYear(2000));
    }
}
