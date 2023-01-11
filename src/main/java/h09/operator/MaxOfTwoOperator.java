package h09.operator;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;
// Comparable vergleichbar, ? = maximale Anzahl
// wenn t größer gleich t2 ist gibt man t zurück ansonsten t2
public class MaxOfTwoOperator <T extends Comparable<? super T>> implements BinaryOperator <T> {
    @Override
    public T apply(T t, T t2) {
        return t.compareTo(t2) >= 0 ? t : t2;
    }
}
