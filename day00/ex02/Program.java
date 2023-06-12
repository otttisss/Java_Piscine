package day00.ex02;

import java.util.Scanner;

public class Program {
    public static final int eof = 42;
    public static boolean isPrimary(int num, int div) {
        boolean prime = true;

        while ((div * div <= num) && (num % div != 0)) {
            div += 1;
        }
        if (div * div <= num)
            prime = false;
        return prime;
    }

    public static int sumOfNums(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int number = scanner.nextInt();

        if (number < 2) {
            System.err.println("Illegal Argument");
            System.exit(-1);
        }

        int sum = 0;
        int count = 0;
        while (number != eof) {
            sum = sumOfNums(number);
            if (isPrimary(sum, 2) == true) {
                count++;
            }
            number = scanner.nextInt();
        }
        scanner.close();
        System.out.println("Count of coffee-request - " + count);
    }
}
