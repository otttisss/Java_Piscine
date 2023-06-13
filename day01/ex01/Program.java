package day01.ex01;

public class Program {
    public static void main(String[] args) {
        User[] users = new User[10];
        for (int i = 0; i < 10; i++) {
            users[i] = new User(("User" + (i + 1)), i * 100);
            System.out.println(users[i]);
        }
        System.out.println("LastID: " + UserIdsGenerator.getId());
    }
}
