package W5.wyrazenia;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Klasa reprezentujaca stala
 */
public class Liczba extends Operand {

    private final double val;

    public Liczba(double val) throws WyjatekONP {
        this.val = val;
        sprawdz(val);
    }

    /**
     * @see Obliczalny
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        return val;
    }

    @Override
    public String toString() {
        return Double.toString(val);
    }
}
