package W4.wyrazenia;


/**
 * Operator logarytmu
 */
public class Logarytm extends Binary {
    /**
     * Logarytm o podstawie w z x
     *
     * @param w podstawa logarytmu
     * @param x liczba logarytmowana
     */
    public Logarytm(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return Math.log(super.x.oblicz()) / Math.log(super.w.oblicz());
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return "log(" + super.w.toString() + "," + super.x.toString() + ")";
    }
}
