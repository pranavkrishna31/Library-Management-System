package util;

public class Validation {

    public static boolean isEmpty(String input) {
        return input == null || input.trim().isEmpty();
    }

    public static boolean isPositive(int num) {
        return num > 0;
    }

    public static void showError(String message) {
        System.out.println("ERROR: " + message);
    }
}