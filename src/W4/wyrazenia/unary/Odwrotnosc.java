package W4.wyrazenia.unary;

import W4.wyrazenia.Unary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Odwrotnosc extends Unary {

    public Odwrotnosc(@NotNull Wyrazenie w) {
        super(w);
    }

    @Override
    public double oblicz() {
        return (1f) / super.w.oblicz();
    }

    @Override
    public String toString() {
        return "1/" + super.w.toStringFormat();
    }
}
