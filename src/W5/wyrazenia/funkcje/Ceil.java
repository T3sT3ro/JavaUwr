package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Sufit
 */
public class Ceil extends Unary {

    @Override
    public String toString() {
        return "ceil";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.ceil(super.arg);
    }
}
