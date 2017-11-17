package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Czesc ulamkowa
 */
public class Frac extends Unary {

    @Override
    public String toString() {
        return "frac";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return super.arg - Math.floor(super.arg);
    }
}
