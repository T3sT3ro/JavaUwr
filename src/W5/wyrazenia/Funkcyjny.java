package W5.wyrazenia;

import W5.wyrazenia.wyjatki.WyjatekONP;

public interface Funkcyjny extends Obliczalny {
    int arnosc();

    int brakujaceArgumenty();

    void dodajArgument(double arg) throws WyjatekONP;
}
