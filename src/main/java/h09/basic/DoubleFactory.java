package h09.basic;

public class DoubleFactory implements BasicFactory<Double> {

    double current;
    final double step;
    @Override
    public Double create() {
        double result = current;
        current+= step;
        return result;
    }

    public DoubleFactory (double start, double step){
        this.current = start;
        this.step = step;
    }
}
