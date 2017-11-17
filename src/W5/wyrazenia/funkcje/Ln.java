package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Logarytm naturalny
 */
public class Ln extends Unary {

    @Override
    public String toString() {
        return "ln";
    }

    /**
     * @return double log base e of arg
     * @throws WyjatekONP Jesli liczba logarytmowana mniejsza od 0
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        if (Double.compare(arg, 0D) < 0)
            throw new ONP_BladArgumentu("nie mozna logarytmowac liczby ujemnej");
        return Math.log(super.arg);
    }

}
