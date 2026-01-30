package deque;

public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    public int size();
    public T removeFirst();
    public T removeLast();
    public T get(int index);

    default public boolean isEmpty() {
        return size() == 0;
    }

    default public void printDeque() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < size(); i += 1) {
            result.append(get(i));
            result.append(" ");
        }
        System.out.println(result.toString());
    }
}
