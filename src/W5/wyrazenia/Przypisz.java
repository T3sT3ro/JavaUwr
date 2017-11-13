package W5.wyrazenia;

import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.ONP_BledneWyrazenie;
import W5.wyrazenia.wyjatki.WyjatekONP;

/// przypisuje wartość arg1 do arg2
public class Przypisz extends Symbol {

    private Zmienna lhs; // obiekt, do ktorego przypisuje
    private double rhs;  // wartosc przypisywana

    public void setLhs(Symbol lhs) throws ONP_BledneWyrazenie {
        if (!(lhs instanceof Zmienna))
            new ONP_BledneWyrazenie("przypisywac mozna tylko do zmiennej");
        this.lhs = (Zmienna) lhs;
    }

    public void setRhs(double rhs) throws ONP_BladArgumentu {
        this.rhs = rhs;
    }

    @Override
    public String toString() {
        return "=";
    }

    @Override
    public double obliczWartosc() throws WyjatekONP, WyjatekKontenera {
        lhs.ustaw(rhs);
        return rhs;
    }

}
