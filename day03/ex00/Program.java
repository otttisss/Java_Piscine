package day03.ex00;

public class Program {
    public static void main(String[] args) throws Exception {
        int count;
        Hen hen = new Hen();
        Egg egg = new Egg();

        if (args.length != 1)
            throw new Exception("Wrong num of arguments");
        if (args[0].startsWith("--count="))
            count = Integer.parseInt(args[0].substring(8));
        else {
            count = Integer.parseInt(args[0]);
        }
        if (count < 1)
            throw new Exception("Count should be >= 1" + count);

        egg.startThread(count);
        hen.startThread(count);
        egg.start();
        hen.start();
        egg.join();
        hen.join();

        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }
    }
}
