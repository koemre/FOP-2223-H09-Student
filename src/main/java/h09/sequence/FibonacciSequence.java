package h09.sequence;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class FibonacciSequence implements Sequence <Integer> {
// TODO: H3.2 - uncomment if implemented
//    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            int current = 0;
            int next = 1;
            @Override
            public boolean hasNext() {
                return true;
            }

            @Override
            public Integer next() {
                int result =next;
                next+=current;
                current = result;
                return result;
            }
        };
    }
}
