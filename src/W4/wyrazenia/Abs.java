package W4.wyrazenia;


/**
 * Operator wartosci bezwzglednej
 */
public class Abs extends Unary {

    public Abs(Wyrazenie w) {
        super(w);
    }

    /**
     * @see Unary
     */
    @Override
    public double oblicz() {
        return Math.abs(super.w.oblicz());
    }

    /**
     * @see Unary
     */
    @Override
    public String toString() {
        return "|" + super.w.toString() + "|";
    }
}
