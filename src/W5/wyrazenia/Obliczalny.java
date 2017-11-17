package W5.wyrazenia;

import W5.narzedzia.wyjatki.WyjatekKontenera;
import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Interfejs obliczalny, czyli wszystko implementujace go zwraca wartosc przy wywolaniu obliczWartosc()
 */
public interface Obliczalny {
    /**
     * Zwraca wartosc obliczonego wyrazenia na podstawie podanych argumentow
     *
     * @return double wynik
     * @throws WyjatekONP jesli obliczenie zglasza wyjatek obliczania wartosci
     * @throws WyjatekKontenera obliczenie zglasza wyjatek kontenera
     */
    double obliczWartosc() throws WyjatekONP, WyjatekKontenera;
}
