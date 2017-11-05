package W4.wyrazenia.unary;

import W4.wyrazenia.Unary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Cosinus extends Unary {

    public Cosinus(@NotNull Wyrazenie w) {
        super(w);
    }

    @Override
    public double oblicz() {
        return Math.cos(super.w.oblicz());
    }

    @Override
    public String toString() {
        return "cos(" + super.w.toString() + ")";
    }
}
