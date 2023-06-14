package day01.ex04;

public class User {
    private Integer identifier_;
    private String name_;
    private Integer balance_;
    private TransactionsLinkedList linkedList;
    public User(String name, Integer balance) {
        this.identifier_ = UserIdsGenerator.getInstance().generatedId();
        this.name_ = name;
        this.balance_ = balance;
        this.linkedList = new TransactionsLinkedList();
    }

    public User() {
        this.identifier_ = UserIdsGenerator.getInstance().generatedId();
        this.balance_ = 0;
    }

    public TransactionsLinkedList getLinkedList() {
        return linkedList;
    }

    public String getName() {
        return name_;
    }

    public Integer getBalance() {
        return balance_;
    }

    public Integer getIdentifier() {
        return identifier_;
    }

    public void setName(String name) {
        if (name.length() > 0) {
            this.name_ = name;
        }
    }

    public void setBalance(Integer balance) {
        if (balance >= 0) {
            this.balance_ = balance;
        }
    }
}
