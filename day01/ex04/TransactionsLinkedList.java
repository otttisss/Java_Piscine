package day01.ex04;
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
            this.t = t;
            this.prev = prev;
        }
    }

    public TransactionsLinkedList() {}

    public int getSize() {
        return size;
    }

    @Override
    public void addTransaction(Transaction transaction) {
        final Node tmp = this.last;
        final Node newNode = new Node(tmp, transaction, null);
        this.last = newNode;

        if (tmp == null) {
            this.first = newNode;
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
        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            if (tmp.t != null && id.equals(tmp.t.getIdentifier())) {
                deleteNodeFromLinkedList(tmp);
                return;
            }
        }
        throw new TransactionNotFoundException();
    }

    @Override
    public Transaction[] toArray() {
        if (this.size == 0) {
            return null;
        }
        Transaction[] result = new Transaction[this.size];

        int i = 0;
        for (Node tmp = first; tmp != null; tmp = tmp.next) {
            result[i++] = tmp.t;
        }
        return result;
    }

    private void deleteNodeFromLinkedList(Node node) {
        final Node next = node.next;
        final Node prev = node.prev;

        if (prev == null)
            this.first = next;
        else {
            prev.next = next;
            node.prev = null;
        }
        if (next == null)
            this.last = prev;
        else {
            next.prev = prev;
            node.next = null;
        }

        node.t = null;
        size--;
    }
}
