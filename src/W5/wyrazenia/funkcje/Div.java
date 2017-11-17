package W5.wyrazenia.funkcje;

import W5.wyrazenia.wyjatki.ONP_DzieleniePrzezZero;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Dzielenie
 */
public class Div extends Binary {

    @Override
    public String toString() {
        return "/";
    }

    /**
     * @return {@link W5.wyrazenia.Obliczalny}
     * @throws WyjatekONP Jesli dzielenie przez zero, zwroc wyjatek
     */
    @Override
    public double obliczWartosc() throws WyjatekONP {
        if (Double.compare(arg2, 0D) == 0 || Double.compare(arg2, -0D) == 0)
            throw new ONP_DzieleniePrzezZero("nie mozna dzielic przez zero");
        return arg1 / arg2;
    }
}
