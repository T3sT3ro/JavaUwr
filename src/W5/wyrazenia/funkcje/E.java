package W5.wyrazenia.funkcje;

import W5.wyrazenia.Liczba;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Stala E=2.71... (podstawa logarytmu naturalnego)
 */
public class E extends Liczba {

    public E() throws WyjatekONP {
        super(Math.E);
    }

    @Override
    public String toString() {
        return "E";
    }
}
