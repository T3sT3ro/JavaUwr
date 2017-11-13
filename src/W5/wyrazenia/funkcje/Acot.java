package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

public class Acot extends Unary {

    @Override
    public String toString() {
        return "acot";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.PI / 2 - Math.atan(super.arg);
    }
}
