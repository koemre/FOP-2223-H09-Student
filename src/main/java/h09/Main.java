package h09;

import h09.sequence.FibonacciSequence;

import java.util.Iterator;

/**
 * Main entry point in executing the program.
 */
public class Main {

    /**
     * Main entry point in executing the program.
     *
     * @param args program arguments, currently ignored
     */
    public static void main(String[] args) {
        System.out.println("Hello World!");
        FibonacciSequence fibonacciSequence = new FibonacciSequence();
        Iterator<Integer> iterator = fibonacciSequence.iterator();
        for (int i = 0; i < 10; i++) {
            System.out.println(iterator.next());
        }
    }
}
