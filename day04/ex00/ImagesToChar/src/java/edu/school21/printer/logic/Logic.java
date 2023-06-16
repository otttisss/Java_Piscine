package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Logic {

    public static void imageToChar(char white, char black, String path) {
        try {
            BufferedImage image = ImageIO.read(new File(path));

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (image.getRGB(x, y) == Color.BLACK.getRGB())
                        System.out.print(black);
                    else if (image.getRGB(x, y) == Color.WHITE.getRGB())
                        System.out.print(white);
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
