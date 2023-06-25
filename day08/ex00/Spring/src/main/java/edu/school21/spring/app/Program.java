package edu.school21.spring.app;

import edu.school21.spring.preprocessor.PreProcessor;
import edu.school21.spring.preprocessor.PreProcessorToUpperImpl;
import edu.school21.spring.printer.Printer;
import edu.school21.spring.printer.PrinterWithPrefixImpl;
import edu.school21.spring.render.Renderer;
import edu.school21.spring.render.RendererErrImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Program {
    public static void main(String[] args) {
        PreProcessor preProcessor = new PreProcessorToUpperImpl();
        Renderer renderer = new RendererErrImpl(preProcessor);
        PrinterWithPrefixImpl printer = new PrinterWithPrefixImpl(renderer);
        printer.setPrefix("Prefix ");
        printer.print("Hello!");

        ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        Printer printer1 = context.getBean("printerWithPrefix", Printer.class);
        printer1.print("Hello!");
    }
}
