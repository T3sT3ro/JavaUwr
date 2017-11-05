package W4.wyrazenia.unary;

import W4.wyrazenia.Unary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Abs extends Unary {

    public Abs(@NotNull Wyrazenie w) {
        super(w);
    }

    @Override
    public double oblicz() {
        return Math.abs(super.w.oblicz());
    }

    @Override
    public String toString() {
        return "|" + super.w.toString() + "|";
    }
}
