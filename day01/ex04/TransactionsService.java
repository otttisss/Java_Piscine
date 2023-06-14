package day01.ex04;

public class TransactionsService {
    TransactionsList transactionsList = new TransactionsLinkedList();
    UserList userList = new UserArrayList();

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


}
