package day03.ex03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Program {

    private static final String FILE_URLS_PATH = "/Users/otttisss/Desktop/JavaPiscine/day03/ex03/files_urls.txt";

    public static void main(String[] args) {
        int threadsCount = getThreadsCount(args);

        List<String> fileUrls = readFileUrls();
        if (fileUrls.isEmpty()) {
            System.out.println("No file URLs found.");
            return;
        }

        List<DownloadThread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            DownloadThread thread = new DownloadThread(fileUrls);
            thread.start();
            threads.add(thread);
        }

        for (DownloadThread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        System.out.println("All files downloaded.");
    }

    private static int getThreadsCount(String[] args) {
        int threadsCount = 1; // default value

        for (String arg : args) {
            if (arg.startsWith("--threadsCount=")) {
                try {
                    threadsCount = Integer.parseInt(arg.substring(arg.indexOf('=') + 1));
                    break;
                } catch (NumberFormatException e) {
                    // Use default value if the argument is not a valid integer
                }
            }
        }

        return threadsCount;
    }

    private static List<String> readFileUrls() {
        List<String> fileUrls = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_URLS_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                fileUrls.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error reading file URLs: " + e.getMessage());
        }

        return fileUrls;
    }

    private static synchronized void downloadFile(String fileUrl, int fileNumber) {
        try {
            URL url = new URL(fileUrl);
            String fileName = "file_" + fileNumber + ".txt";
            Files.copy(url.openStream(), Paths.get(fileName));
            System.out.println(Thread.currentThread().getName() + " finished download file number " + fileNumber);
        } catch (IOException e) {
            System.out.println("Error downloading file number " + fileNumber + ": " + e.getMessage());
        }
    }

    private static class DownloadThread extends Thread {
        private final List<String> fileUrls;

        public DownloadThread(List<String> fileUrls) {
            this.fileUrls = fileUrls;
        }

        @Override
        public void run() {
            while (!fileUrls.isEmpty()) {
                String fileUrl;
                int fileNumber;

                synchronized (fileUrls) {
                    if (fileUrls.isEmpty()) {
                        break; // No more files to download
                    }

                    fileUrl = fileUrls.remove(0);
                    fileNumber = fileUrls.size() + 1;
                }

                System.out.println(Thread.currentThread().getName() + " start download file number " + fileNumber);
                downloadFile(fileUrl, fileNumber);
            }
        }
    }
}

