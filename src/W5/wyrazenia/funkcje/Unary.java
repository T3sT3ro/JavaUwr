package W5.wyrazenia.funkcje;

import W5.wyrazenia.Funkcja;
import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.WyjatekONP;

public abstract class Unary extends Funkcja {

    protected double arg;
    int dodane = 0;

    public Unary() {
    }

    public Unary(double arg) throws WyjatekONP {
        this.arg = arg;
        dodane = 1;
        sprawdz(arg);
    }

    @Override
    public int arnosc() {
        return 1;
    }

    @Override
    public int brakujaceArgumenty() {
        return arnosc() - dodane;
    }

    @Override
    public void dodajArgument(double arg) throws WyjatekONP {
        if (dodane == 0)
            this.arg = arg;
        else
            throw new ONP_BladArgumentu("za duzo argumentow");
        dodane++;
        sprawdz(arg);
    }

}
