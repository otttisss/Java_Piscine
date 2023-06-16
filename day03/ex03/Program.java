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

    private static final String FILE_URLS_PATH = "day03/ex03/files_urls.txt";

    public static void main(String[] args) {
        int threadsCount = getThreadsCount(args);

        List<FileEntry> fileEntries = readFileEntries();
        if (fileEntries.isEmpty()) {
            System.out.println("No file entries found.");
            return;
        }

        List<DownloadThread> threads = new ArrayList<>();

        for (int i = 0; i < threadsCount; i++) {
            DownloadThread thread = new DownloadThread(fileEntries);
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

    private static List<FileEntry> readFileEntries() {
        List<FileEntry> fileEntries = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_URLS_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ", 2);
                if (parts.length == 2) {
                    int fileNumber = Integer.parseInt(parts[0]);
                    String fileUrl = parts[1].trim();
                    fileEntries.add(new FileEntry(fileNumber, fileUrl));
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file entries: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing file number: " + e.getMessage());
        }

        return fileEntries;
    }

    private static synchronized void downloadFile(String fileUrl, int fileNumber) {
        try {
            URL url = new URL(fileUrl);
            String fileName = "file_" + fileNumber + getFileExtension(fileUrl);
            Files.copy(url.openStream(), Paths.get(fileName));
            System.out.println(Thread.currentThread().getName() + " finished download file number " + fileNumber);
        } catch (IOException e) {
            System.out.println("Error downloading file number " + fileNumber + ": " + e.getMessage());
        }
    }

    private static String getFileExtension(String fileUrl) {
        String extension = "";

        int dotIndex = fileUrl.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileUrl.length() - 1) {
            extension = fileUrl.substring(dotIndex);
        }

        return extension;
    }

    private static class FileEntry {
        private final int fileNumber;
        private final String fileUrl;

        public FileEntry(int fileNumber, String fileUrl) {
            this.fileNumber = fileNumber;
            this.fileUrl = fileUrl;
        }

        public int getFileNumber() {
            return fileNumber;
        }

        public String getFileUrl() {
            return fileUrl;
        }
    }

    private static class DownloadThread extends Thread {
        private final List<FileEntry> fileEntries;

        public DownloadThread(List<FileEntry> fileEntries) {
            this.fileEntries = fileEntries;
        }

        @Override
        public void run() {
            while (true) {
                FileEntry fileEntry;

                synchronized (fileEntries) {
                    if (fileEntries.isEmpty()) {
                        break; // No more files to download
                    }

                    fileEntry = fileEntries.remove(0);
                }

                int fileNumber = fileEntry.getFileNumber();
                String fileUrl = fileEntry.getFileUrl();

                System.out.println(Thread.currentThread().getName() + " start download file number " + fileNumber);
                downloadFile(fileUrl, fileNumber);
            }
        }
    }
}

