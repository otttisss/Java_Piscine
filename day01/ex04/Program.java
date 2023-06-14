package day01.ex04;
import java.util.UUID;
public class Program {
    public static void main(String[] args) {
        TransactionsService service = new TransactionsService();
        System.out.println("**********************************");
        System.out.println("Create 2 users: ");
        User user1 = new User("Peer", 100000);
        User user2 = new User("Peer2", 200000);
        System.out.println(user1.getName() + " balance: " + user1.getBalance());
        System.out.println(user2.getName() + " balance: " + user2.getBalance());

        service.addUser(user1);
        service.addUser(user2);
        System.out.println("**********************************");
        System.out.println("Let's make 3 transactions and print users balance💸: ");
        service.executeTransactions(user1.getIdentifier(), user2.getIdentifier(), 50000);
        service.executeTransactions(user1.getIdentifier(), user2.getIdentifier(), 30000);
        service.executeTransactions(user2.getIdentifier(), user1.getIdentifier(), 10000);

        System.out.println("Balance " + user1.getName() + " = " + service.getUserBalance(user1));
        System.out.println("Balance " + user2.getName() + " = " + service.getUserBalance(user2.getIdentifier()));
        System.out.println("**********************************");
        System.out.println("Print all transactions of user " + user1.getName());
        Transaction[] arrayTransactionUser1 = service.getTransactionList(user1.getIdentifier());
        for (int i = 0; i < arrayTransactionUser1.length; i++) {
            arrayTransactionUser1[i].printTransferInfo();
        }

        System.out.println("**********************************");
        System.out.println("Delete transaction of user " + user1.getName());
        UUID idTr = arrayTransactionUser1[1].getIdentifier();
        service.removeTransactionById(idTr, user1.getIdentifier());
        arrayTransactionUser1 = service.getTransactionList(user1.getIdentifier());
        for (int i = 0; i < arrayTransactionUser1.length; i++) {
            arrayTransactionUser1[i].printTransferInfo();
        }

        System.out.println("**********************************");
        System.out.println("Print all transactions of user " + user2.getName());
        Transaction[] arrayTransactionUser2 = service.getTransactionList(user2.getIdentifier());
        for (int i = 0; i < arrayTransactionUser2.length; i++) {
            arrayTransactionUser2[i].printTransferInfo();
        }

        System.out.println("**********************************");
        System.out.println("Checking exception work");
        try {
            service.executeTransactions(user1.getIdentifier(), user2.getIdentifier(), 40000);
        } catch (IllegalTransactionException ex) {
            System.out.println(ex);
        }
        User user3 = new User("Peer3", 10000);
        try {
            service.getUserBalance(user3);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
