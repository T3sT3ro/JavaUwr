package W4.wyrazenia;

/**
 * Klasa abstrakcyjna reprezentujaca operator jednoargumentowy
 */
public abstract class Unary extends Wyrazenie {

    protected final Wyrazenie w;

    /**
     * Inicjalizuje wyrazenie, jesli jest null, zglasza wyjatek.
     *
     * @param w wyrazenie
     */
    public Unary(Wyrazenie w) {
        if (w == null)
            throw new IllegalArgumentException("Wyrazenie nie moze byc puste!");
        this.w = w;
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
