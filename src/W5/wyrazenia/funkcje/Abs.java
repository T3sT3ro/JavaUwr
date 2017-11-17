package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Wartosc bezwzgledna
 */
public class Abs extends Unary {

    @Override
    public String toString() {
        return "abs";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.abs(super.arg);
    }
}
