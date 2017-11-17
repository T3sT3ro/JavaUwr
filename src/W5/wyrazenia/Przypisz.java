package W5.wyrazenia;

import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.ONP_BledneWyrazenie;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * operator przypisania
 */
public class Przypisz extends Symbol {

    private Zmienna lhs; // obiekt, do ktorego przypisuje
    private double rhs;  // wartosc przypisywana

    /**
     * Ustawia lewy operand (do ktorego bedziemy przypisywac)
     *
     * @param lhs obiekt typu Zmienna
     * @throws ONP_BledneWyrazenie jesli lhs nie jest zmienna
     * @see Zmienna
     */
    public void setLhs(Symbol lhs) throws ONP_BledneWyrazenie {
        if (!(lhs instanceof Zmienna))
            new ONP_BledneWyrazenie("przypisywac mozna tylko do zmiennej");
        this.lhs = (Zmienna) lhs;
    }

    /**
     * Ustawia prawy operand przypisania (wartosc przypisywana)
     *
     * @param rhs Double, wartosc
     * @throws ONP_BladArgumentu tak jak funkcja sprawdz {@link Symbol}
     */
    public void setRhs(double rhs) throws WyjatekONP {
        sprawdz(rhs);
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "=";
    }

    /**
     * Wywoluje dodanie nowej zmiennej lub zmiane istniejacej i zwraca jej nowa wartosc
     * @see Zmienna
     * @return nowa wartosc zmiennej w lhs
     * @throws WyjatekONP jak w Zmienna.ustaw {@link Zmienna}
     * @throws WyjatekKontenera jak w Zmienna.ustaw {@link Zmienna}
     */
    @Override
    public double obliczWartosc() throws WyjatekONP, WyjatekKontenera {
        lhs.ustaw(rhs);
        return rhs;
    }

}
