package h09.operator;

import java.util.function.BinaryOperator;

public class DoubleSumWithCoefficientsOperator implements BinaryOperator <Double>{

    @Override
    public Double apply(Double aDouble, Double aDouble2) {
        return aDouble * coeff1 + aDouble2 * coeff2;
    }
    private final Double coeff1;

    /**
     * Second coefficient.
     */
    private final Double coeff2;
    public DoubleSumWithCoefficientsOperator(Double coeff1, Double coeff2) {
        // Assign first parameter to first coefficient
        this.coeff1 = coeff1;

        // Assign second parameter to second coefficient
        this.coeff2 = coeff2;
    }

}

