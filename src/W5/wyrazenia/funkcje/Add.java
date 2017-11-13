package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

public class Add extends Binary {

    @Override
    public String toString() {
        return "+";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return arg1 + arg2;
    }
}
