package edu.school21.spring.printer;
import edu.school21.spring.render.Renderer;
public class PrinterWithPrefixImpl implements Printer {
    private Renderer render;
    private String prefix;

    public PrinterWithPrefixImpl(Renderer render) {
        this.render = render;
    }

    @Override
    public void print(String s) {
        render.renderMsg(prefix + s);
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
