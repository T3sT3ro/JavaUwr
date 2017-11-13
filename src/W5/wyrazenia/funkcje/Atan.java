package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

public class Atan extends Unary {

    @Override
    public String toString() {
        return "atan";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.atan(super.arg);
    }
}
