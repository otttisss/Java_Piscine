package day00.ex04;

import java.util.Scanner;

public class Program {
    private static final int maxCodes = 65535;
    private static final int maxChartHeight = 10;
    private static final int topSize = 10;


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        scanner.close();
        short[] charCounter =countChar(string);
        char[] top = getTop(charCounter);
        printChars(top, charCounter);
    }

    private static short[] countChar(String line) {
        short[] tmp = new short[maxCodes];

        for (int i = 0; i < line.length(); i++) {
            tmp[line.toCharArray()[i]]++;
        }
        return tmp;
    }

    private static char[] getTop(short[] charCount) {
        char[] tmp = new char[maxCodes];

        for (int i = 0; i < maxCodes; i++) {
            short count = charCount[i];
            if (count > 0) {
                for (int j = 0; j < maxCodes; j++) {
                    if (charCount[tmp[j]] < count) {
                        tmp = insertChar(tmp, (char)i, j);
                        break;
                    }
                }
            }
        }
        return tmp;
    }

    private static char[] insertChar(char[] base, char c, int index) {
        char[] tmp = new char[maxCodes];

        for (int i = 0; i < index; i++) {
            tmp[i] = base[i];
        }
        tmp[index] = c;
        for (int i = index + 1; i < maxCodes; i++) {
            tmp[i] = base[i - 1];
        }
        return tmp;
    }

    private static void printChars(char[] top, short[] charCount) {
        short max = charCount[top[0]];
        final int maxHeight = (max <= maxChartHeight ? max : maxChartHeight);
        final int totalLines = maxHeight + 2;
        int[] charts = new int[maxCodes];

        for (int i = 0; i < maxCodes; i++) {
            if (max <= 10)
                charts[i] = charCount[top[i]];
            else
                charts[i] = charCount[top[i]] * maxChartHeight / max;
        }
        System.out.println();

        for (int i = 0; i < totalLines; i++) {
            for (int j = 0; j < topSize; j++) {
                if (top[j] != 0) {
                    if (i + charts[j] + 2 == totalLines) {
                        System.out.printf("%3d", charCount[top[j]]);
                    } else if (i == totalLines - 1) {
                        System.out.printf("%3c", top[j]);
                    } else if (i + charts[j] >= maxHeight) {
                        System.out.printf("%3c", '#');
                    }
                    if (j != maxCodes - 1 && top[j + 1] != 0 && i + charts[j + 1] >= maxHeight) {
                        System.out.printf("%c", ' ');
                    }
                }
            }
            System.out.println();
        }
    }
}
