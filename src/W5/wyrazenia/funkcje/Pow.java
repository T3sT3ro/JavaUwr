package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Potegowanie
 */
public class Pow extends Binary {

    @Override
    public String toString() {
        return "^";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.pow(arg1, arg2);
    }
}
