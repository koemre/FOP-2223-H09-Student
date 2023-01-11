package h09.operator;

import h09.basic.BasicBinaryOperations;

import java.util.function.BinaryOperator;

public class SumWithCoefficientsOperator <X,Y> implements BinaryOperator <X> {

    final Y coeff1;
    final Y coeff2;
    final BasicBinaryOperations<X, Y> op;
    public SumWithCoefficientsOperator (BasicBinaryOperations<X, Y> op, Y coeff1, Y coeff2){
    this.coeff1 = coeff1;
    this.coeff2 = coeff2;
    this.op = op;
    }

    @Override
    public X apply(X x, X x2) {
        // return left * coeff1 + right * coeff2;
        return op.add(op.mul(x,coeff1),  op.mul(x2,coeff2));
    }
}
