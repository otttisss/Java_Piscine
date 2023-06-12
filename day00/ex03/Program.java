package day00.ex03;

import java.util.Scanner;

public class Program {
    public static final int minGrade = 1;
    public static final int maxGrade = 9;
    public static final int maxWeeks = 18;
    public static final int maxGrades = 5;

    private static int getMin(Scanner scanner) {
        int min = 9;
        int tmp;

        for (int i = 0; i < maxGrades; i++) {
            tmp = scanner.nextInt();
            if (tmp > maxGrade || tmp < minGrade){
                System.err.println("Illegal Argument");
                System.exit(-1);
            }
//            if (min < tmp) {
//                return min;
//            }
//            else {
//                min = tmp;
//            }
            min = (min < tmp) ? min : tmp;
        }
        scanner.nextLine();
        return min;
    }
    private static int getGrade(int range, int week, long info) {
        int ret;

        for (; range < week - 1; range++) {
            info /= 10;
        }
        ret = (int)(info % 10);

        return ret;
    }
    private static void printWeek(int minGrade) {
        for (int i = 0; i < minGrade; i++) {
            System.out.print("=");
        }
        System.out.println(">");
    }
    public static void main(String[] args) {
        int week = 0;
        long info = 0;
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        while (week < maxWeeks && !str.equals("42")) {
            week++;
            if (!str.equals("Week " + week)) {
                System.err.println("IllegalArgument");
                System.exit(-1);
            }
            info *= 10;
            info += getMin(scanner);
            str = scanner.nextLine();
        }
        scanner.close();

        for (int i = 0; i < week; i++) {
            System.out.println("Week " + (i + 1) + " ");
            printWeek(getGrade(i, week, info));
        }
    }
}
