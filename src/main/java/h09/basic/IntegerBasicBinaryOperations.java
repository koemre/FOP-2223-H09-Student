package h09.basic;

public class IntegerBasicBinaryOperations implements BasicBinaryOperations<Integer,Integer>  {

    @Override
    public Integer add(Integer first, Integer second) {
        return first + second;
    }

    @Override
    public Integer mul(Integer first, Integer second) {
        return first * second;
    }
}
