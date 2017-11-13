package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

public class Sgn extends Unary {

    @Override
    public String toString() {
        return "Sgn";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.signum(super.arg);
    }
}
