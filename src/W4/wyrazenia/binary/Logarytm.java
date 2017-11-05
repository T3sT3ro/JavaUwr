package W4.wyrazenia.binary;

import W4.wyrazenia.Binary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

public class Logarytm extends Binary {
    /**
     * Logarytm o podstawie w z x
     *
     * @param w podstawa logarytmu
     * @param x liczba logarytmowana
     */
    public Logarytm(@NotNull Wyrazenie w, @NotNull Wyrazenie x) {
        super(w, x);
    }

    @Override
    public double oblicz() {
        return Math.log(super.x.oblicz()) / Math.log(super.w.oblicz());
    }

    @Override
    public String toString() {
        return "log(" + w.toString() + "," + x.toString() + ")";
    }
}
