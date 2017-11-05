package W4.wyrazenia.binary;

import W4.wyrazenia.Binary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Max extends Binary {
    public Max(@NotNull Wyrazenie w, @NotNull Wyrazenie x) {
        super(w, x);
    }

    @Override
    public double oblicz() {
        return Math.max(super.w.oblicz(), super.w.oblicz());
    }

    @Override
    public String toString() {
        return "MAX(" + w.toString() + "," + x.toString() + ")";
    }
}
