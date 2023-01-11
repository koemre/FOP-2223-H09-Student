package h09.basic;

public class StringFactory implements BasicFactory<String>{

    int current;
    final String[] text;

    public StringFactory(int start, String[] text) {
        this.current = start;
        this.text = text;
    }

    @Override
    public String create() {
        String result = text[current];
        current++;
        if (current == text.length){
            current = 0;
        }
        return result;
    }
}
