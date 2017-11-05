package W4.wyrazenia.unary;

import W4.wyrazenia.Unary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Przeciwienstwo extends Unary {

    public Przeciwienstwo(@NotNull Wyrazenie w) {
        super(w);
    }

    @Override
    public double oblicz() {
        return super.w.oblicz() * (-1f);
    }

    @Override
    public String toString() {
        return "-" + super.w.toStringFormat();
    }
}
