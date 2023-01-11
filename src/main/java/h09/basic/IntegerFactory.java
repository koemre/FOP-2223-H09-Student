package h09.basic;

public class IntegerFactory implements BasicFactory<Integer> {
    int current;
    final int step;


    public IntegerFactory(int start, int step) {
        this.current = start;
        this.step = step;
    }

    @Override
    public Integer create() {
        int result = current;
        current+= step;
        return result;
    }


//Implementieren Sie analog zu IntegerFactory die Methode create in DoubleFactory.
//Der Unterschied ist, dass diese create Methode einen Double-Wert zurückgeben soll.
//Nutzen Sie ein Objektattribut namens current vom Typ double und eine Objektkonstante
// namens step vom Typ double, die Sie im Konstruktor initialisieren.

}
