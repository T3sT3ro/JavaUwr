package W4.wyrazenia.unary;

import W4.wyrazenia.Unary;
import W4.wyrazenia.Wyrazenie;
import org.jetbrains.annotations.NotNull;

/**
 * Accepts angle in radians and returns sinus
 */
public class Sinus extends Unary {

    public Sinus(@NotNull Wyrazenie w) {
        super(w);
    }

    @Override
    public double oblicz() {
        return Math.sin(super.w.oblicz());
    }

    @Override
    public String toString() {
        return "sin(" + super.w.toString() + ")";
    }
}
