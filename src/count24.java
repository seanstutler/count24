import java.util.Arrays;
import java.util.Scanner;

public class count24 {
    public static void main(String[] args) {
        printIntro();
        Scanner scanner = new Scanner(System.in);
        double[] numbers = new double[4];
        boolean again = true;
        while (again) {
            for (int i = 1; i <= 4; i++) {
                System.out.println("Please enter the " + i + " number: ");
                double input = scanner.nextDouble();
                numbers[i - 1] = input;
            }
            arrange(numbers);
            System.out.println();
            System.out.print("Press any key to continue, press q to exit. ");
            String response = scanner.next();
            if (response.equals("q")) {
                again = false;
            }
        }
    }

    private static void printIntro() {
        System.out.println("This program lets you enter 4 digits and calculate if they can be calculated to 24");
        System.out.println();
    }

    private static void arrange(double[] numbers) {
        boolean found = false;
        String[] arithmetics = {"+", "-", "*", "/"};
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                for (int k = 0; k < 4; k++) {
                    String first = arithmetics[i];
                    String second = arithmetics[j];
                    String third = arithmetics[k];
                    String formula = numbers[0] + first + numbers[1] + second + numbers[2] + third + numbers[3];
                    double result = calculate(numbers[0], first, numbers[1], second, numbers[2], third, numbers[3]);
                    if (result == 24.0) {
                        System.out.println(formula + "=" + result);
                        found = true;
                    }
                }
            }
        }
        if (!found) {
            System.out.println("Result not found");
        }
    }

    private static double calculate(double a, String first, double b, String second, double c, String third, double d) {
        if (first.equals("*" ) || first.equals("/")
                || second.equals("*") || second.equals("/")
                || third.equals("*") || third.equals("/")) {
            if (first.equals("*")) {
                return calculateSecond(a*b, second, c, third, d);
            }
            if (first.equals("/")) {
                return calculateSecond(a/b, second, c, third, d);
            }
            if (second.equals("*")) {
                return calculateSecond(a, first, b*c, third, d);
            }
            if (second.equals("/")) {
                return calculateSecond(a, first, b/c, third, d);
            }
            if (third.equals("*")){
                return calculateSecond(a, first, b, second, c*d);
            }
            if (third.equals("/")) {
                return calculateSecond(a, first, b, second, c/d);
            }
        } else {
            if (first.equals("+")) {
                return calculateSecond(a+b, second, c, third, d);
            } else {
                return calculateSecond(a-b, second, c, third, d);
            }
        }
        return 0.0;
    }

    private static double calculateSecond(double a, String first, double b, String second, double c) {
        if (first.equals("*") || first.equals("/")
            || second.equals("*") || second.equals("/")) {
            if (first.equals("*")) {
                return calculateThird(a*b, second, c);
            }
            if (first.equals("/")) {
                return calculateThird(a/b, second, c);
            }
            if (second.equals("*")) {
                return calculateThird(a, first, b*c);
            }
            if (second.equals("/")) {
                return calculateThird(a, first, b/c);
            }
        } else if (first.equals("+")) {
            return calculateThird(a + b, second, c);
        } else {
            return calculateThird(a-b, second, c);
        }
        return 0.0;
    }

    private static double calculateThird(double a, String first, double b) {
        if (first.equals("+")) {
            return a + b;
        } else if (first.equals("-")) {
            return a - b;
        } else if (first.equals("*")) {
            return a * b;
        } else {
            return a / b;
        }
    }
}
