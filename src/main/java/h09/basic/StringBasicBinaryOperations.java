package h09.basic;

public class StringBasicBinaryOperations implements BasicBinaryOperations<String,Integer> {


    @Override
    public String add(String first, String second) {
        return first + second;
    }

    @Override
    public String mul(String first, Integer second) {
        if (second <= 0){
            return "";
        } else {
            String a = "";
            while(second != 0){
                a+=first;
                second--;
            }
            return a;
        }
    }
// alle Methoden, die nicht void sind, müssen ein return haben


}
