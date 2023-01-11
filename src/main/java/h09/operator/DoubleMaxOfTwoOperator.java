package h09.operator;

import java.util.function.BinaryOperator;


public class DoubleMaxOfTwoOperator implements BinaryOperator <Double> {

    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        return Math.max(aDouble, aDouble2);
    }

}
