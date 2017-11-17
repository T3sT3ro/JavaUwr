package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Sinus
 */
public class Sin extends Unary {

    @Override
    public String toString() {
        return "sin";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.sin(super.arg);
    }
}
