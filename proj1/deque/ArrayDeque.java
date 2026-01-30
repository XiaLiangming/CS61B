package deque;

public class ArrayDeque<T> implements Deque<T> {
    protected int capacity;
    protected int size;
    protected int beginDex;
    protected T[] items;

    @SuppressWarnings("unchecked")
    public ArrayDeque() {
        capacity = 8;
        size = 0;
        beginDex = 0;
        items = (T []) new Object[capacity];
    }

    public void addFirst(T item) {
        if (size == capacity) resize(capacity * 4);
        beginDex = Math.floorMod(beginDex - 1, capacity);
        items[beginDex] = item;
        size += 1;
    }

    public void addLast(T item) {
        if (size == capacity) resize(capacity * 4);
        items[(size + beginDex) % capacity] = item;
        size += 1;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) return null;
        T item = items[beginDex];
        beginDex = (beginDex + 1) % capacity;
        size -= 1;
        if (size < capacity / 4) resize(capacity / 2);
        return item;
    }

    public T removeLast() {
        if (size == 0) return null;
        T item = items[(size + beginDex - 1) % capacity];
        size -= 1;
        if (size < capacity / 4) resize(capacity / 2);
        return item;
    }

    public T get(int index) {
        if (index < 0 || index >= size) return null;
        return items[(index + beginDex) % capacity];
    }

    @SuppressWarnings("unchecked")
    protected void resize(int capacity) {
        T[] newItems = (T []) new Object[capacity];
        // Unroll
        int headLen = Math.min(this.capacity - beginDex, size);
        System.arraycopy(items, beginDex, newItems, 0, headLen);
        if (beginDex != 0) {
            int tailLen = size - headLen;
            System.arraycopy(items, 0, newItems, headLen, tailLen);
        }
        this.capacity = capacity;
        items = newItems;
        beginDex = 0;
    }
}
