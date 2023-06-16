package day03.ex02;

import java.util.Arrays;

public class Program {
    private static int[] array;
    private static int[] threadSums;

    public static void main(String[] args) {
        int arraySize = Integer.parseInt(getArgumentValue(args, "--arraySize"));
        int threadsCount = Integer.parseInt(getArgumentValue(args, "--threadsCount"));

        array = generateRandomArray(arraySize);
        int expectedSum = calculateSum(array);
        System.out.println("Sum: " + expectedSum);

        threadSums = new int[threadsCount];
        Thread[] threads = new Thread[threadsCount];

        int sectionSize = arraySize / threadsCount;
        int remainingElements = arraySize % threadsCount;
        int startIndex = 0;
        int endIndex = sectionSize - 1;

        for (int i = 0; i < threadsCount; i++) {
            if (i == threadsCount - 1) {
                endIndex += remainingElements;
            }

            threads[i] = new SummingThread(i, startIndex, endIndex);
            threads[i].start();

            startIndex = endIndex + 1;
            endIndex = startIndex + sectionSize - 1;
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int sumByThreads = Arrays.stream(threadSums).sum();
        System.out.println("Sum by threads: " + sumByThreads);
    }

    private static String getArgumentValue(String[] args, String argumentName) {
        for (String arg : args) {
            if (arg.startsWith(argumentName + "=")) {
                return arg.substring(argumentName.length() + 1);
            }
        }
        return null;
    }

    private static int[] generateRandomArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 1000);
        }
        return array;
    }

    private static int calculateSum(int[] array) {
        return Arrays.stream(array).sum();
    }

    private static class SummingThread extends Thread {
        private final int threadIndex;
        private final int startIndex;
        private final int endIndex;

        public SummingThread(int threadIndex, int startIndex, int endIndex) {
            this.threadIndex = threadIndex;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            int sum = 0;
            for (int i = startIndex; i <= endIndex; i++) {
                sum += array[i];
            }
            synchronized (threadSums) {
                threadSums[threadIndex] = sum;
            }
            System.out.println("Thread " + (threadIndex + 1) + ": from " + startIndex + " to " + endIndex + " sum is " + sum);
        }
    }
}

