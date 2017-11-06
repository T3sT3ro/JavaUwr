package W4.wyrazenia;

/**
 * Operator dzielenia
 */
public class Dzielenie extends Binary {
    /**
     * Dzielenie w przez x
     *
     * @param w dzielna
     * @param x dzielnik
     */
    public Dzielenie(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return super.w.oblicz() / super.x.oblicz();
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return super.w.toStringFormat() + "/" + super.x.toStringFormat();
    }
}
