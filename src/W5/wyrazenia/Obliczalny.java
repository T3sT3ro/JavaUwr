package W5.wyrazenia;

import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.wyjatki.WyjatekONP;

public interface Obliczalny {
    double obliczWartosc() throws WyjatekONP, WyjatekKontenera;
}
