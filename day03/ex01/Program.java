package day03.ex01;

public class Program {
    public static boolean thread = false;
    public static void main(String[] args) throws Exception {
        int count;

        if (args.length != 1)
            throw new Exception("Wrong num of arguments");
        if (args[0].startsWith("--count="))
            count = Integer.parseInt(args[0].substring(8));
        else {
            count = Integer.parseInt(args[0]);
        }
        if (count < 1)
            throw new Exception("Count should be >= 1" + count);
        Hen hen = new Hen(count);
        Egg egg = new Egg(count);

        try {
            egg.start();
            hen.start();
//            egg.join();
//            hen.join();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void printHens() throws InterruptedException {
        if (!thread)
            Program.class.wait();
        System.out.println("Hen");
        thread = !thread;
        Program.class.notify();
    }

    public static synchronized void printEggs() throws InterruptedException {
        if (thread)
            Program.class.wait();
        System.out.println("Egg");
        thread = !thread;
        Program.class.notify();
    }
}
