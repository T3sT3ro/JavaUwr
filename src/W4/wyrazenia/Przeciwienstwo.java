package W4.wyrazenia;

/**
 * Operator pzreciwienstwa wyrazenia w (-w)
 */
public class Przeciwienstwo extends Unary {

    public Przeciwienstwo(Wyrazenie w) {
        super(w);
    }

    /**
     * @see Unary
     */
    @Override
    public double oblicz() {
        return super.w.oblicz() * (-1f);
    }

    /**
     * @see Unary
     */
    @Override
    public String toString() {
        return "-" + super.toStringFormat();
    }
}
