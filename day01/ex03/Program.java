package day01.ex03;

public class Program {
    public static void main(String[] args) {
        User user = new User("John", 10000);
        User user1 = new User("Kira", 20000);

        TransactionsLinkedList linkedList = user.getLinkedList();
        TransactionsLinkedList linkedList1 = user1.getLinkedList();

        Transaction transaction = new Transaction(user, user1, 500);
        Transaction transaction1 = new Transaction(user1, user, 5000);
        Transaction transaction2 = new Transaction(user, user1, 300);

        linkedList.addTransaction(transaction);
        linkedList1.addTransaction(transaction1);
        linkedList.addTransaction(transaction2);

        System.out.println("John's quantity of transactions: " + user.getLinkedList().getSize());
        System.out.println("Kira's quantity of transactions: " + user1.getLinkedList().getSize());

        linkedList.removeById(transaction2.getIdentifier());
        System.out.println("Linkedlist after deleting second transaction: " + linkedList.getSize());

        Transaction[] array = linkedList.toArray();
        for (Transaction t : array) {
            System.out.println(t.getSender().getName() + " " + t.getIdentifier());
        }
    }
}
