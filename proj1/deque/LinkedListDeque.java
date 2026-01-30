package deque;

public class LinkedListDeque<T> implements Deque<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        public Node(T item) {
            this.item = item;
            prev = this;
            next = this;
        }

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null);
        size = 0;
    }

    public LinkedListDeque(T item) {
        this();
        sentinel.next = new Node(item, this.sentinel, this.sentinel);
        sentinel.prev = this.sentinel.next;
        size += 1;
    }

    @Override
    public void addFirst(T item) {
        Node origFirst = this.sentinel.next;
        sentinel.next = new Node(item, this.sentinel, origFirst);
        origFirst.prev = this.sentinel.next;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node origLast = this.sentinel.prev;
        sentinel.prev = new Node(item, origLast, this.sentinel);
        origLast.next = this.sentinel.prev;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder result = new StringBuilder();
        Node p = sentinel;
        for (int idx = 0; idx < size; idx += 1) {
            p = p.next;
            result.append(p.item);
            result.append(" ");
        }
        System.out.println(result.toString());
    }

    @Override
    public T removeFirst() {
        if (size == 0) return null;
        T item = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) return null;
        T item = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) return null;
        Node p = this.sentinel.next;
        for (int idx = 0; idx < index; idx += 1) {
            p = p.next;
        }
        return p.item;
    }
}
