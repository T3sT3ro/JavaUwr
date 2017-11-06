package W4.wyrazenia;

/**
 * Operator potegowania
 */
public class Potegowanie extends Binary {
    /**
     * Podniesienie w do potegi x
     *
     * @param w podstawa
     * @param x wykladnik
     */
    public Potegowanie(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return Math.pow(super.w.oblicz(), super.x.oblicz());
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return super.w.toStringFormat() + "^" + super.x.toStringFormat();
    }
}
