package W4.wyrazenia;


/**
 * Operator odwrotnosci wyrazenia w (1/w)
 */
public class Odwrotnosc extends Unary {

    public Odwrotnosc(Wyrazenie w) {
        super(w);
    }

    /**
     * @see Unary
     */
    @Override
    public double oblicz() {
        return (1f) / super.w.oblicz();
    }

    /**
     * @see Unary
     */
    @Override
    public String toString() {
        return "1/" + super.w.toStringFormat();
    }
}
