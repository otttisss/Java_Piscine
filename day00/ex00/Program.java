public class Program {
    public static int getSum(int num) {
        int sum = 0;
        sum += num / 100000;
        num %= 100000;

        sum += num / 10000;
        num %= 10000;

        sum += num / 1000;
        num %= 1000;

        sum += num / 100;
        num %= 100;

        sum += num / 10;
        num %= 10;

        sum += num;
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(getSum(479598));
    }
}