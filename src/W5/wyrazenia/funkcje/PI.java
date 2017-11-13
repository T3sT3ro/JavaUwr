package W5.wyrazenia.funkcje;

import W5.wyrazenia.Liczba;
import W5.wyrazenia.wyjatki.WyjatekONP;

public class PI extends Liczba {

    public PI() throws WyjatekONP {
        super(Math.PI);
    }

    @Override
    public String toString() {
        return "PI";
    }
}
