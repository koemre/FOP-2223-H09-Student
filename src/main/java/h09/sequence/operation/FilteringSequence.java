package h09.sequence.operation;

import h09.sequence.Sequence;

import java.util.Iterator;
import java.util.function.Predicate;

import static org.tudalgo.algoutils.student.Student.crash;

public class FilteringSequence <T>/*TODO: H4.1*/ implements Sequence <T> {
    private final Sequence sequence;
    private final Predicate predicate;

    public FilteringSequence(Sequence sequence, Predicate predicate) {
        this.sequence = sequence;
        this.predicate = predicate;
    }

    // TODO: H4.1 - uncomment if implemented
//    @Override
    public Iterator<T> iterator() {
        final Iterator iterator;
        return new Iterator<>() {
            // TODO: H4.1 - attributes here
            @Override
            public boolean hasNext() {
                return crash(); // TODO: H4.1 - remove if implemented
            }

            @Override
            public /*TODO: H4.1 replace*/T next() {
                return crash(); // TODO: H4.1 - remove if implemented
            }
        };
    }
}
