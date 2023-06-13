package day01.ex00;

public class Program {
    public static void main(String[] args) {
        User user = new User("John", 1000);
        printUserInfo(user);
        User user1 = new User("Jake", 1500);
        printUserInfo(user1);

        Transaction transDebit = new Transaction(user1, user, 300);
        printTransactions(transDebit);
        printUserInfo(user1);
        printUserInfo(user);
        Transaction transCredit = new Transaction(user1, user, -200);
        printTransactions(transCredit);
        printUserInfo(user1);
    }
    private static void printUserInfo(User user) {
        System.out.println(user.getIdentifier() + " name: " + user.getName() + " balance: " + user.getBalance());
    }

    private static void printTransactions(Transaction transaction) {
        System.out.println("Name: " + transaction.getSender().getName() + " send to " + transaction.getRecipient().getName()
        + " " + transaction.getAmount() + " Category: " + transaction.getCategory() + " UUID: " + transaction.getIdentifier());
    }
}
