package day01.ex04;
import java.util.UUID;

public class TransactionsService {
    TransactionsList transactionsList = new TransactionsLinkedList();
    UserList userList = new UserArrayList();
    TransactionsLinkedList invalidTransaction = new TransactionsLinkedList();

    public void addUser(User user) {
        this.userList.addUser(user);
    }

    public int getUserBalance(int id) {
        return userList.getUserId(id).getBalance();
    }

    public int getUserBalance(User user) {
        int id = user.getIdentifier();

        for (int i = 0; i < userList.getListSize(); i++) {
            if (id == userList.getUserIndex(i).getIdentifier()) {
                return user.getBalance();
            }
        }
        throw new UserNotFoundException();
    }

    public Transaction[] getTransactionList(int id) {
        return userList.getUserId(id).getLinkedList().toArray();
    }

    public void removeTransactionById(UUID tID, int userId) {
        userList.getUserId(userId).getLinkedList().removeById(tID);
    }

    public void executeTransactions(int senderId, int recipientId, int amount) {
        User sender = userList.getUserId(senderId);
        User recipient = userList.getUserId(recipientId);

        if (senderId == recipientId || amount < 0 || sender.getBalance() < amount) {
            throw new IllegalTransactionException("IllegalTransaction");
        }

        Transaction credit = new Transaction(recipient, sender, -amount);
        Transaction debit = new Transaction(recipient, sender, amount);

        debit.setIdentifier(credit.getIdentifier());
        recipient.getLinkedList().addTransaction(debit);
        sender.getLinkedList().addTransaction(credit);

        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    private TransactionsLinkedList getAllLinkedList() {
        TransactionsLinkedList result = new TransactionsLinkedList();

        for (int i = 0; i < userList.getListSize(); i++) {
            User user = userList.getUserIndex(i);
            if (user != null) {
                int size = user.getLinkedList().getSize();
                for (int j = 0; j < size; j++) {
                    result.addTransaction(user.getLinkedList().toArray()[j]);
                }
            }
        }
        return result;
    }
    public Transaction[] getInvalidTransaction() {
        TransactionsLinkedList result = new TransactionsLinkedList();
        TransactionsLinkedList allLinkedList = getAllLinkedList();
        Transaction[] array = allLinkedList.toArray();

        if (array != null) {
            int arraySize = array.length;
            for (int i = 0; i < arraySize; i++) {
                int count = 0;
                for (int j = 0; j < arraySize; j++) {
                    if (array[i].getIdentifier().equals(array[j].getIdentifier()))
                        count++;
                }
                if (count != 2)
                    result.addTransaction(array[i]);
            }
        }
        return result.toArray();
    }
}
