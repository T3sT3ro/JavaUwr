package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * podloga
 */
public class Cos extends Unary {

    @Override
    public String toString() {
        return "cos";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.cos(super.arg);
    }
}
