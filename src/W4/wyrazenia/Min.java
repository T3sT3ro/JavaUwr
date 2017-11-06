package W4.wyrazenia;


/**
 * Operator wartosci minimalnej
 */
public class Min extends Binary {
    public Min(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return Math.min(super.w.oblicz(), super.x.oblicz());
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return "MIN(" + super.w.toString() + "," + super.x.toString() + ")";
    }
}
