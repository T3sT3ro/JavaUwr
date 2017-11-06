package W4.wyrazenia;

/**
 * Operator odejmowania
 */
public class Odejmowanie extends Binary {
    /**
     * Odejmowanie x od w
     *
     * @param w odjemna
     * @param x odjemnik
     */
    public Odejmowanie(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return super.w.oblicz() - super.x.oblicz();
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return super.w.toStringFormat() + "-" + super.x.toStringFormat();
    }
}
