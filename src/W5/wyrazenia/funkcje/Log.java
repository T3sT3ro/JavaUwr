package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Logarytm przy danej podstawie
 */
public class Log extends Binary {

    @Override
    public String toString() {
        return "log";
    }

    /**
     * @return log base arg1 of arg2
     * @throws WyjatekONP jesli liczba logarytmowana  lub podstawa mniejsza od 0 albo rowna 1
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        if (Double.compare(arg2, 0D) < 0)
            throw new ONP_BladArgumentu("nie mozna logarytmowac liczby ujemnej");
        if (Double.compare(arg1, 0D) < 0 || Double.compare(arg1, 1D) == 0)
            throw new ONP_BladArgumentu("podstawa logarytmu musi byc >0 i =/=1");

        return Math.log(arg2) / Math.log(arg1);
    }
}
