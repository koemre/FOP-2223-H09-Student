package h09.basic;

public class DoubleBasicBinaryOperations implements BasicBinaryOperations<Double, Double> {
    @Override
    public Double add(Double first, Double second) {
        return first + second;
    }

    @Override
    public Double mul(Double first, Double second) {
        return first * second;
    }

}
