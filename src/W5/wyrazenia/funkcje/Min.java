package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Minimum
 */
public class Min extends Binary {

    @Override
    public String toString() {
        return "min";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.min(arg1, arg2);
    }
}
