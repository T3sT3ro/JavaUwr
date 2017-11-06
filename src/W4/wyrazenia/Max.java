package W4.wyrazenia;


/**
 * Operator wartosci maksymalnej
 */
public class Max extends Binary {
    public Max(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return Math.max(super.w.oblicz(), super.x.oblicz());
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return "MAX(" + super.w.toString() + "," + super.x.toString() + ")";
    }
}
