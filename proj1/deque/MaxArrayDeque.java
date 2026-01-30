package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator; 
    public MaxArrayDeque(Comparator<T> c) {
        super();
        comparator = c;
    }

    public T max() {
        return max(comparator);
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T maxItem = get(0);
        for (int index = 1; index < size(); index += 1) {
            T thisItem = get(index);
            if (c.compare(thisItem, maxItem) > 0) {
                maxItem = thisItem;
            }
        }
        return maxItem;
    }
}
