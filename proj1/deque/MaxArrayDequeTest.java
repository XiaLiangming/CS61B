package deque;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Comparator;

/** Performs some basic comparator tests. */
public class MaxArrayDequeTest {
    public class Dog {
        private int weightInPounds;
        private String name;

        public Dog(int w, String n) {
            weightInPounds = w;
            name = n;
        }

        public int getWeight() {
            return weightInPounds;
        }

        public String getName() {
            return name;
        }
    }

    public class DogComparator implements Comparator<Dog> {
        @Override
        public int compare(Dog d1, Dog d2) {
            return d1.getWeight() - d2.getWeight();
        }
    }

    @Test
    public void integerComparatorTest() {
        Comparator<Integer> naturalComparator = Comparator.naturalOrder();
        Comparator<Integer> reverseComparator = Comparator.reverseOrder();
        MaxArrayDeque<Integer> L = new MaxArrayDeque<Integer>(naturalComparator);
        L.addLast(1);
        L.addFirst(2);
        assertEquals((Integer) 2, L.max());
        assertEquals((Integer) 1, L.max(reverseComparator));
    }

    @Test
    public void stringComparatorTest() {
        Comparator<String> naturalComparator = Comparator.naturalOrder();
        Comparator<String> reverseComparator = Comparator.reverseOrder();
        MaxArrayDeque<String> L = new MaxArrayDeque<String>(naturalComparator);

		assertTrue("A newly initialized LLDeque should be empty", L.isEmpty());
		L.addFirst("apple");
        L.addFirst("banana");
        assertEquals("banana", L.max());
        assertEquals("apple", L.max(reverseComparator));
    }

    @Test
    public void dogComparatorTest() {
        MaxArrayDeque<Dog> L = new MaxArrayDeque<>(new DogComparator());
        L.addLast(new Dog(12, "Mike"));
        L.addLast(new Dog(100, "Jack"));
        L.addLast(new Dog(125, "Jenny"));
        Dog maxDog = L.max();
        assertEquals("Jenny", maxDog.getName());
    }
}
