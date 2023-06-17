package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

public class Program {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Add arguments [0] white symbol [1] black symbol");
            System.exit(-1);
        }
        Logic.imageToChar(args[0].charAt(0), args[1].charAt(0));
    }
}
