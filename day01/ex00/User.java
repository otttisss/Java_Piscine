package day01.ex00;

public class User {
    private Integer identifier_;
    private String name_;
    private Integer balance_;
    private static Integer id_ = 0;

    public User(String name, Integer balance) {
        id_++;
        this.identifier_ = id_;
        this.name_ = name;
        this.balance_ = balance;
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
