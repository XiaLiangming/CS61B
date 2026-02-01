package tester;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class demo {
    public void main(String[] args) {
        ByteArrayOutputStream outBuffer = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;

        System.setOut(new PrintStream(outBuffer));
        System.out.println("Hello");
        String p1 = outBuffer.toString();
        outBuffer.reset();
        System.out.println("World");
        String p2 = outBuffer.toString();
        System.setOut(originalOut);
        System.out.println("1st print: " + p1);
        System.out.println("2nd print: " + p2);
    }
}
