package day00.ex01;
import java.util.Scanner;
public class Program {
    public static void main(String[] args) {
        boolean isPrime = true;

        Scanner scanner = new Scanner(System.in);
        int num = scanner.nextInt();
        scanner.close();

        if (num < 2) {
            System.err.println("IllegalArgument");
            System.exit(-1);
        }
        int i = 2;
        for (; i * i <= num ; i++) {
            if (num % i == 0) {
                isPrime = false;
                break;
            }
        }
        System.out.println(isPrime + " " + (i - 1));
    }
}
