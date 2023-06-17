package edu.school21.printer.logic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static com.diogonunes.jcolor.Attribute.*;
import static com.diogonunes.jcolor.Ansi.colorize;

public class Logic {

    public static void imageToChar(Color white, Color black) {
        if (white == null || black == null) {
            System.err.println("Wrong colors. Try --white=RED --black=GREEN");
        }

        try {
            BufferedImage image = ImageIO.read(Logic.class.getResource("/resources/it.bmp"));

            for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    if (image.getRGB(x, y) == Color.BLACK.getRGB())
                        System.out.print(colorize(" ", BACK_COLOR(black.getRed(), black.getGreen(), black.getBlue())));
                    else if (image.getRGB(x, y) == Color.WHITE.getRGB())
                        System.out.print(colorize(" ", BACK_COLOR(white.getRed(), white.getBlue(), white.getBlue())));
                }
                System.out.println();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
