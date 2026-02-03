package tester;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import edu.princeton.cs.introcs.StdRandom;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    @Test
    public void randomTest() {
        Random rand = new Random();

        StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> tad = new ArrayDequeSolution<>();

        String ls = System.lineSeparator();
        StringBuilder msg = new StringBuilder();
        
        msg.append("isEmpty()" + ls);
        assertEquals(msg.toString(), tad.isEmpty(), sad.isEmpty());
        
        for (int i = 0; i < 1000; i += 1){
            double numberBetweenZeroAndOne = StdRandom.uniform();
            
            if (numberBetweenZeroAndOne < 0.125) {
                int randomItem = rand.nextInt(100);
                sad.addFirst(randomItem);
                tad.addFirst(randomItem);
                msg.append("addFirst(" + randomItem + ")" + ls);
            } else if (numberBetweenZeroAndOne < 0.25) {
                int randomItem = rand.nextInt(100);
                sad.addLast(randomItem);
                tad.addLast(randomItem);
                msg.append("addLast(" + randomItem + ")" + ls);
            } else if (numberBetweenZeroAndOne < 0.375) {
                msg.append("isEmpty()" + ls);
                assertEquals(msg.toString(), tad.isEmpty(), sad.isEmpty());
            } else if (numberBetweenZeroAndOne < 0.5) {
                msg.append("size()" + ls);
                assertEquals(msg.toString(), tad.size(), sad.size());
            } else if (numberBetweenZeroAndOne < 0.625) {
                // ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
                // PrintStream originalOut = System.out;
        
                // System.setOut(new PrintStream(outBuffer));
                // sad.printDeque();
                // String sadOut = outBuffer.toString();
                // outBuffer.reset();
                // tad.printDeque();
                // String tadOut = outBuffer.toString();
                // outBuffer.reset();
                // msg.append("printDeque()" + ls);
                // System.setOut(originalOut);
                // assertEquals(msg.toString(), tadOut, sadOut);
            } else if (numberBetweenZeroAndOne < 0.75) {
                if (sad.isEmpty() && tad.isEmpty()) continue;
                String failureMsg = msg.toString() + "isEmpty()" + ls;
                assertEquals(failureMsg, tad.isEmpty(), sad.isEmpty());
                msg.append("removeFirst()" + ls);
                assertEquals(msg.toString(), tad.removeFirst(), sad.removeFirst());
            } else if (numberBetweenZeroAndOne < 0.875) {
                if (sad.isEmpty() && tad.isEmpty()) continue;
                String failureMsg = msg.toString() + "isEmpty()" + ls;
                assertEquals(failureMsg, tad.isEmpty(), sad.isEmpty());
                msg.append("removeLast()" + ls);
                assertEquals(msg.toString(), tad.removeLast(), sad.removeLast());
            } else {
                if (sad.isEmpty() && tad.isEmpty()) continue;
                String failureMsg = msg.toString() + "size()" + ls;
                assertEquals(failureMsg, tad.size(), sad.size());
                int randomDex = rand.nextInt(tad.size());
                msg.append("get(" + randomDex + ")" + ls);
                assertEquals(msg.toString(), tad.get(randomDex), sad.get(randomDex));
            }
        }
    }
}
