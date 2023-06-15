package day02.ex00;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    private static final String signaturesPath = "day02/ex00/signatures.txt";
    private static final String processed = "PROCESSED";
    private static final String undefined = "UNDEFINED";
    private static final String outputFilePath = "day02/ex00/result.txt";
    public static void main(String[] args)  throws IOException {
       Map<String, String> signaturesMap = new HashMap<>();

        FileInputStream fileInputStream = new FileInputStream(signaturesPath);
        byte[] buffer = new byte[fileInputStream.available()];
        fileInputStream.read(buffer, 0, fileInputStream.available());

        String str = "";
        for (int i = 0; i < buffer.length; i++) {
            if ((char) buffer[i] != ' ') {
                str += (char)buffer[i];
            }
        }

        String[] splitSignature = str.split("\n");
        for (int i = 0; i < splitSignature.length; i++) {
            String[] pair = splitSignature[i].split(",");
            signaturesMap.put(pair[1], pair[0]);
        }

        fileInputStream.close();

        Scanner scanner = new Scanner(System.in);
        String path = scanner.nextLine();
        final int maxLen = 42;

        while (!path.equals("42")) {
            String signaturesFile = "";

            FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath, true);
            FileInputStream fileInput = new FileInputStream(path);

            for (int i = 0; fileInput.available() > 0 && i < maxLen; i++) {
                signaturesFile += (String.format("%02X", fileInput.read()));
            }
            String value = "";
            for (String key : signaturesMap.keySet()) {
                if (signaturesFile.startsWith(key)) {
                    value = signaturesMap.get(key);
                    fileOutputStream.write(value.getBytes());
                    fileOutputStream.write('\n');
                    System.out.println(processed);
                }
            }
            if (value == "")
                System.out.println(undefined);
            path = scanner.nextLine();
        }
        scanner.close();
    }
}
