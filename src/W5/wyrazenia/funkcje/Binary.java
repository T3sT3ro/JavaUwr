package W5.wyrazenia.funkcje;

import W5.wyrazenia.Funkcja;
import W5.wyrazenia.wyjatki.ONP_BladArgumentu;
import W5.wyrazenia.wyjatki.WyjatekONP;

public abstract class Binary extends Funkcja {

    protected double arg1;
    protected double arg2;
    int dodane = 0;

    public Binary() {
    }

    public Binary(double arg1, double arg2) throws WyjatekONP {
        this.arg1 = arg1;
        this.arg2 = arg2;
        dodane = 2;
        sprawdz(arg1);
        sprawdz(arg2);
    }

    @Override
    public int arnosc() {
        return 2;
    }

    @Override
    public int brakujaceArgumenty() {
        return arnosc() - dodane;
    }

    @Override
    public void dodajArgument(double arg) throws WyjatekONP {
        if (dodane == 0)
            this.arg2 = arg;
        else if (dodane == 1)
            this.arg1 = arg;
        else
            throw new ONP_BladArgumentu("za duzo argumentow");
        dodane++;
        sprawdz(arg1);
        sprawdz(arg2);
    }
}
