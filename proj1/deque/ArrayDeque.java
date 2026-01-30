package deque;
import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {
    private int capacity;
    private int size;
    private int beginDex;
    private T[] items;

    
    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        capacity = 8;
        size = 0;
        beginDex = 0;
        items = (T[]) new Object[capacity];
    }
    
    @Override
    public void addFirst(T item) {
        if (size == capacity) {
            resize(capacity * 4);
        }
        beginDex = Math.floorMod(beginDex - 1, capacity);
        items[beginDex] = item;
        size += 1;
    }
    
    @Override
    public void addLast(T item) {
        if (size == capacity) {
            resize(capacity * 4);
        }
        items[(size + beginDex) % capacity] = item;
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
        for (int i = 0; i < size(); i += 1) {
            result.append(get(i));
            result.append(" ");
        }
        System.out.println(result.toString());
    }
    
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T item = items[beginDex];
        beginDex = (beginDex + 1) % capacity;
        size -= 1;
        if (size < capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }
    
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T item = items[(size + beginDex - 1) % capacity];
        size -= 1;
        if (size < capacity / 4) {
            resize(capacity / 2);
        }
        return item;
    }
    
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return items[(index + beginDex) % capacity];
    }
    
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
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

    @SuppressWarnings("unchecked")
    protected void resize(int newCapacity) {
        T[] newItems = (T[]) new Object[newCapacity];
        // Unroll
        int headLen = Math.min(capacity - beginDex, size);
        System.arraycopy(items, beginDex, newItems, 0, headLen);
        if (beginDex != 0) {
            int tailLen = size - headLen;
            System.arraycopy(items, 0, newItems, headLen, tailLen);
        }
        capacity = newCapacity;
        items = newItems;
        beginDex = 0;
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int index;
    
        ArrayDequeIterator() {
            index = 0;
        }
    
        @Override
        public boolean hasNext() {
            return index < size;
        }
    
        @Override
        public T next() {
            T item = get(index);
            index += 1;
            return item;
        }
    }
}
