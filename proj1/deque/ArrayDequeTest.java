package deque;

import org.junit.Test;
import static org.junit.Assert.*;


/** Performs some basic array list tests. */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<String> L = new ArrayDeque<String>();

		assertTrue("A newly initialized Leque should be empty", L.isEmpty());
		L.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, L.size());
        assertFalse("L should now contain 1 item", L.isEmpty());

		L.addLast("middle");
		assertEquals(2, L.size());

		L.addLast("back");
		assertEquals(3, L.size());

		System.out.println("Printing out deque: ");
		L.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
		// should be empty
		assertTrue("L should be empty upon initialization", L.isEmpty());

		L.addFirst(10);
		// should not be empty
		assertFalse("L should contain 1 item", L.isEmpty());

        assertEquals((Integer) 10, L.get(0));

		L.removeFirst();
		// should be empty
		assertTrue("L should be empty after removal", L.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> L = new ArrayDeque<>();
        L.addFirst(3);

        L.removeLast();
        L.removeFirst();
        L.removeLast();
        L.removeFirst();

        int size = L.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create ArrayDeques with different parameterized types*/
    public void multipleParamTest() {
        ArrayDeque<String>  L = new ArrayDeque<String>();
        ArrayDeque<Double>  L2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> L3 = new ArrayDeque<Boolean>();

        L.addFirst("string");
        L2.addFirst(3.14159);
        L3.addFirst(true);
    }

    @Test
    /* Check if null is return when removing from an empty ArrayDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();

        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, L.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, L.removeLast());

    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            L.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) L.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) L.removeLast(), 0.0);
        }

    }

    @Test
    /* Check if method get works correctly.*/
    public void printDequeTest() {
        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
        ArrayDeque<Integer> L = new ArrayDeque<Integer>();
        L.addLast(5);
        L.addLast(10);
        L.addLast(15);
        L.addFirst(0);
        assertEquals((Integer) 0, L.get(0));
        assertEquals((Integer) 5, L.get(1));
        assertEquals((Integer) 10, L.get(2));
        assertEquals((Integer) 15, L.get(3));
    }

    @Test
    public void equalsTest() {
        ArrayDeque<Integer> L1 = new ArrayDeque<>();
        ArrayDeque<Integer> L2 = new ArrayDeque<>();
        LinkedListDeque<Integer> L3 = new LinkedListDeque<>();
        LinkedListDeque<Integer> L4 = new LinkedListDeque<>();
        for (int i = 0; i < 10; i += 1) {
            L1.addFirst(i);
            L3.addLast(i);
        }
        for (int i = 0; i < 10; i += 1) {
            L2.addLast(i);
            L4.addLast(i);
        }
        L2.addLast(null);
        L4.addLast(null);
        assertFalse(L1.equals(L3));
        assertTrue(L2.equals(L4));
    }

    @Test
    public void iteratorTest() {
        ArrayDeque<Integer> L = new ArrayDeque<>();
        for (int i = 0; i < 10; i += 1) {
            L.addLast(i * 2 + 1);
        }

        int index = 0;
        for (int item : L) {
            assertEquals(index * 2 + 1, item);
            index += 1;
        }
    }
}
