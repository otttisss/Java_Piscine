package edu.school21.printer.app;

import edu.school21.printer.logic.Arguments;
import edu.school21.printer.logic.Colors;
import edu.school21.printer.logic.Logic;
import com.beust.jcommander.JCommander;
public class Program {
    public static void main(String[] args) {
        Arguments arguments = new Arguments();
        JCommander.newBuilder().addObject(arguments).build().parse(args);

        if (args.length != 2) {
            System.err.println("Add arguments [0] white symbol [1] black symbol");
            System.exit(-1);
        }
        Logic.imageToChar(Colors.getColor(arguments.getWhite()), Colors.getColor(arguments.getBlack()));
    }
}
