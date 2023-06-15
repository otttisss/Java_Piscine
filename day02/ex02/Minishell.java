package day02.ex02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

public class Minishell {
    private String cwd;

    public Minishell(String cwd) {
        this.cwd = cwd;
    }

    private void ls() {
        for (File file : new File(cwd).listFiles()) {
            System.out.println(file.getName() + " " + file.length() / 1024 + " KB");
        }
    }

    private void cd(String directory) {
        Path currentDirectory = Paths.get(cwd);
        Path nextDirectory = currentDirectory.resolve(Paths.get(directory));

        if (!Files.exists(nextDirectory) || !Files.isDirectory(nextDirectory)) {
            System.out.println("cd: such no directory" + Paths.get(directory));
        } else {
            this.cwd = nextDirectory.toString();
        }
    }

    private void mv(String target, String dir) {
        Path targetP = Paths.get(cwd).resolve(Paths.get(target));
        Path newDir = Paths.get(cwd).resolve(Paths.get(dir));

        if (!Files.exists(Paths.get(cwd).resolve(Paths.get(target)))) {
            System.out.println("mv: " + target + ": No such file or directory");
            return;
        }

        if (Files.isDirectory(newDir)) {
            newDir = Paths.get(newDir + "/" + new File(target).getName());
        }

        try {
            Files.move(targetP, newDir, REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        String str;


        while (true) {
            str = scanner.nextLine().trim();

            String [] args = str.split(" ");

            if (args.length == 0) {
                System.err.println("Incorrect number of arguments");
            } else if (args[0].equals("ls")) {
                ls();
            } else if (args[0].equals("cd") && args.length == 2) {
                cd(args[1]);
            } else if (args[0].equals("mv") && args.length == 3) {
                mv(args[1], args[2]);
            } else if (args[0].equals("exit")) {
                System.exit(0);
            } else {
                System.err.println("ERROR");
            }
        }
    }
}
