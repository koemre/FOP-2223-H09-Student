package h09.sequence;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class ArraySequence <T> implements Sequence <T> {
    private T[] values;

    public ArraySequence (T[] values){
        this.values = values;
    }
// TODO: H3.1 - uncomment if implemented
//    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            int  index = 0;
            @Override
            public boolean hasNext() {
                if (index < values.length)
                    return true;
                return false;
            }

            @Override
            public /*TODO: H3.1 replace*/T next() {
                int inde = index;
                index++;
                return values[inde];
            }
        };
    }
}
