package W4.wyrazenia.binary;

import W4.wyrazenia.Binary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Potegowanie extends Binary {
    public Potegowanie(@NotNull Wyrazenie w, @NotNull Wyrazenie x) {
        super(w, x);
    }

    @Override
    public double oblicz() {
        return Math.pow(super.w.oblicz(), super.w.oblicz());
    }

    @Override
    public String toString() {
        return w.toStringFormat() + "^" + x.toStringFormat();
    }
}
