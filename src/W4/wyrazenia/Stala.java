package W4.wyrazenia;

/**
 * Prymitywny typ przechowujacy stala
 */
public class Stala extends Wyrazenie {

    private final double value;

    /**
     * Inicjuje stala
     *
     * @param value
     */
    public Stala(double value) {
        this.value = value;
    }

    /**
     * @see Wyrazenie
     */
    @Override
    public double oblicz() {
        return value;
    }

    /**
     * @see Wyrazenie
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
