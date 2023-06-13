package day01.ex03;

import java.util.UUID;

public interface TransactionsList {
    void addTransaction(Transaction transaction);
    void removeById(UUID id);
    Transaction[] toArray();
}
