package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T item) {
            this.item = item;
            prev = this;
            next = this;
        }

        Node(T item, Node prev, Node next) {
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
    public boolean isEmpty() {
        return size == 0;
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
        if (size == 0) {
            return null;
        }
        T item = this.sentinel.next.item;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.prev = this.sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = this.sentinel.prev.item;
        this.sentinel.prev = this.sentinel.prev.prev;
        this.sentinel.prev.next = this.sentinel;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        Node p = this.sentinel.next;
        for (int idx = 0; idx < index; idx += 1) {
            p = p.next;
        }
        return p.item;
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (!(o instanceof Deque)) {
            return false;
        }

        Deque<?> other = (Deque<?>) o;
        if (size() != other.size()) {
            return false;
        }

        for (int index = 0; index < size(); index += 1) {
            T thisItem = get(index);
            Object otherItem = other.get(index);
            if (thisItem == null) {
                if (otherItem != null) {
                    return false;
                }
            } else if (!thisItem.equals(otherItem)) {
                return false;
            }
        }

        return true;
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int index;
        private Node p;
        LinkedListDequeIterator() {
            index = 0;
            p = sentinel.next;
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            T item = p.item;
            p = p.next;
            index += 1;
            return item;
        }
    }    
}
