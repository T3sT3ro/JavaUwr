package W4.wyrazenia;

/**
 * Operator cosinusa kata w radianach
 */
public class Cosinus extends Unary {

    public Cosinus(Wyrazenie w) {
        super(w);
    }

    /**
     * @see Unary
     */
    @Override
    public double oblicz() {
        return Math.cos(super.w.oblicz());
    }

    /**
     * @see Unary
     */
    @Override
    public String toString() {
        return "cos(" + super.w.toString() + ")";
    }
}
