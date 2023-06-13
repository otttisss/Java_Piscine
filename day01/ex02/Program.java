package day01.ex02;

public class Program {
    public static void main(String[] args) {
        User[] users = new User[14];

        for (int i = 0; i < 14; i++) {
            users[i] = new User(("User" + (i + 1)), i * 1000);
        }

        UsersArrayList usersArrayList = new UsersArrayList();
        for (int i = 0; i < 14; i++) {
            usersArrayList.addUser(users[i]);
        }
        usersArrayList.printUsersInfo();

        User user1 = usersArrayList.getUserId(13);
        User user2 = usersArrayList.getUserIndex(13);
        System.out.println(user1.getIdentifier() + " " + user1.getName());
        System.out.println(user2.getIdentifier() + " " + user2.getName());
    }
}
