package edu.school21.spring.printer;

import edu.school21.spring.render.Renderer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PrinterWithDateTimeImpl implements Printer {
    private Renderer renderer;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");

    public PrinterWithDateTimeImpl(Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public void print(String s) {
        renderer.renderMsg("[" + LocalDateTime.now().format(FORMATTER) + "] " + s);
    }
}
