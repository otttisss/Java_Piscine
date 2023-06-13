package day01.ex00;

import java.util.UUID;

public class Transaction {
    private UUID identifier_;
    private User recipient_;
    private User sender_;
    private Integer amount_;

    private enum Category {
        DEBIT,
        CREDIT
    }

    private Category category_;

    public Transaction(User recipient, User sender, Integer amount) {
        this.recipient_ = recipient;
        this.sender_ = sender;
        this.amount_ = amount;
        identifier_ = UUID.randomUUID();

        if (amount < 0)
            category_ = Category.CREDIT;
        else
            category_ = Category.DEBIT;

        if (sender.getBalance() < 0 || sender.getBalance() < amount) {
            System.err.println("Transaction error");
        } else {
            sender.setBalance(sender.getBalance() - amount);
            recipient.setBalance(recipient.getBalance() + amount);
        }
    }

    public UUID getIdentifier() {
        return identifier_;
    }

    public User getRecipient() {
        return recipient_;
    }

    public User getSender() {
        return sender_;
    }

    public Integer getAmount() {
        return amount_;
    }

    public void setIdentifier(UUID identifier) {
        this.identifier_ = identifier;
    }

    public void setRecipient(User recipient) {
        this.recipient_ = recipient;
    }

    public void setSender(User sender) {
        this.sender_ = sender;
    }

    public void setAmount(Integer amount) {
        this.amount_ = amount;
    }

    public Category getCategory() {
        return category_;
    }
}
