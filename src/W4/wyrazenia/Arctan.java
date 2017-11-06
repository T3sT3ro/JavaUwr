package W4.wyrazenia;


/**
 * Operator zwracajacy arcus tangens
 */
public class Arctan extends Unary {

    public Arctan(Wyrazenie w) {
        super(w);
    }

    /**
     * @see Unary
     */
    @Override
    public double oblicz() {
        return Math.atan(super.w.oblicz());
    }

    /**
     * @see Unary
     */
    @Override
    public String toString() {
        return "atan(" + super.w.toString() + ")";
    }
}
