package W4.wyrazenia;


/**
 * Operator sinusa kata w radianach
 */
public class Sinus extends Unary {

    public Sinus(Wyrazenie w) {
        super(w);
    }

    /**
     * @see Unary
     */
    @Override
    public double oblicz() {
        return Math.sin(super.w.oblicz());
    }

    /**
     * @see Unary
     */
    @Override
    public String toString() {
        return "sin(" + super.w.toString() + ")";
    }
}
