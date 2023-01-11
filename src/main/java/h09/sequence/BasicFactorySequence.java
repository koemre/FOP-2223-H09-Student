package h09.sequence;

import h09.basic.BasicFactory;

import java.util.Iterator;

import static org.tudalgo.algoutils.student.Student.crash;

public class BasicFactorySequence <T>implements Sequence <T>{
final BasicFactory<T> factory;
    public BasicFactorySequence (BasicFactory<T> factory){


        this.factory = factory;
    }
// TODO: H3.3 - uncomment if implemented
//    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return crash(); // TODO: H3.3 - remove if implemented
            }

            @Override
            public /*TODO: H3.3 replace*/T next() {
                return factory.create();
            }
        };
    }
}
