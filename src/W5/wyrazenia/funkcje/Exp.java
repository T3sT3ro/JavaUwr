package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Stala e Eulera podniesiona do potegi
 */
public class Exp extends Unary {

    @Override
    public String toString() {
        return "exp";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.exp(super.arg);
    }
}
