package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

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
