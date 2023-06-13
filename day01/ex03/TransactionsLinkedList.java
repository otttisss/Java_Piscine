package day01.ex03;

import java.util.UUID;

public class TransactionsLinkedList implements TransactionsList {
    private int size;
    private Node first;
    private Node last;

    private static class Node {
        Transaction t;
        Node next;
        Node prev;

        Node(Node prev, Transaction t, Node next) {
            this.next = next;
            this.prev = prev;
            this.t = t;
        }
    }
    @Override
    public void addTransaction(Transaction transaction) {
        final Node tmp = last;
        final Node newNode = new Node(tmp, transaction, null);
        last = newNode;

        if (tmp == null) {
            first = newNode;
        } else {
            tmp.next = newNode;
        }
        size++;
    }

    @Override
    public void removeById(UUID id) throws TransactionNotFoundException{
        if (id == null) {
            throw new TransactionNotFoundException();
        }
    }

    @Override
    public Transaction[] toArray() {
        return new Transaction[0];
    }
}
