package W4.wyrazenia.unary;


import W4.wyrazenia.Unary;
import W4.wyrazenia.Wyrazenie;

public class Arctan extends Unary {

    public Arctan(Wyrazenie w) {
        super(w);
    }

    @Override
    public double oblicz() {
        return Math.atan(super.w.oblicz());
    }

    @Override
    public String toString() {
        return "atan(" + super.w.toString() + ")";
    }
}
