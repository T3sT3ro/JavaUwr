package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.WyjatekONP;

public class Max extends Binary {

    @Override
    public String toString() {
        return "max";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP {
        return Double.max(arg1, arg2);
    }
}
