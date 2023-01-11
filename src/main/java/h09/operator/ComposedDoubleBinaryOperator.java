package h09.operator;

import java.util.function.BinaryOperator;
import java.util.function.DoubleBinaryOperator;


public class ComposedDoubleBinaryOperator implements BinaryOperator <Double> {
    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        // Apply first operator to given parameters
        double result1 = op1.apply(aDouble, aDouble2);

        // Apply second operator to given parameters
        double result2 = op2.apply(aDouble, aDouble2);

        // Return application of third operator to the intermediate results
        return op3.apply(result1, result2);
    }
    private final BinaryOperator<Double> op1;

    /**
     * Second operator.
     */
    private final BinaryOperator<Double> op2;

    /**
     * Third operator.
     */
    private final BinaryOperator<Double> op3;
    public ComposedDoubleBinaryOperator(BinaryOperator<Double> op1, BinaryOperator<Double> op2, BinaryOperator<Double> op3) {
        // Assign first parameter to first operator
        this.op1 = op1;

        // Assign second parameter to second operator
        this.op2 = op2;

        // Assign third parameter to third operator
        this.op3 = op3;
    }

}
