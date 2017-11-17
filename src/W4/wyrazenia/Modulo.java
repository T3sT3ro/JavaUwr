package W4.wyrazenia;


/**
 * Operator reszty z dzielenia
 */
public class Modulo extends Binary {
    /**
     * Reszta z dzielenia liczby w przez x
     * @param w first argument
     * @param x second argument
     */
    public Modulo(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    /**
     * @see Binary
     */
    @Override
    public double oblicz() {
        return super.w.oblicz() % super.x.oblicz();
    }

    /**
     * @see Binary
     */
    @Override
    public String toString() {
        return super.w.toStringFormat() + "%" + super.x.toStringFormat();
    }
}
