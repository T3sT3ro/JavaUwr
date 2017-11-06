package W4.wyrazenia;


/**
 * Operator doajacy dwa wyrazenia
 */
public class Dodawanie extends Binary {

    public Dodawanie(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return super.w.oblicz() + super.x.oblicz();
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return super.w.toStringFormat() + "+" + super.x.toStringFormat();
    }
}
