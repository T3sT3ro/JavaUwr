package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

public class Floor extends Unary {

    @Override
    public String toString() {
        return "floor";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Math.floor(super.arg);
    }
}
