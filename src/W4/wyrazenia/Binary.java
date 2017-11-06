package W4.wyrazenia;

/**
 * Klasa abstrakcyjna reprezentujaca operator dwuargumentowy
 */
public abstract class Binary extends Unary {

    protected final Wyrazenie x;

    /**
     * Inicjalizuje lewe i prawe wyrazenia, jesli ktorekolwiek jest null, zglasza wyjatek.
     *
     * @param w pierwsze wyrazenie
     * @param x drugie wyrazenie
     * @see Unary
     */
    public Binary(Wyrazenie w, Wyrazenie x) {
        super(w);
        if (x == null)
            throw new IllegalArgumentException("Wyrazenie nie moze byc puste!");
        this.x = x;
    }

    /**
     * @see Wyrazenie
     */
    public abstract double oblicz();

    /**
     * @see Wyrazenie
     */
    public abstract String toString();
}
