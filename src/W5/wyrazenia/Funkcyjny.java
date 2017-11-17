package W5.wyrazenia;

import W5.wyrazenia.wyjatki.WyjatekONP;

/**
 * Interfejs funkcyjny dziedziczacy po Obliczalnym. Kazda funkcja posiada arnosc, dodania argumentu i sprawdzenia ilu brakuje
 */
public interface Funkcyjny extends Obliczalny {
    /**
     * Zwraca arnosc funkcji
     *
     * @return {0,1,2}
     */
    int arnosc();

    /**
     * Mowi ile argumentow nalezy jeszcze dostarczyc
     * @return arnosc - dodane
     */
    int brakujaceArgumenty();

    /**
     * Dodaje argumenty do funkcji
     * @param arg wartosc typu double
     * @throws WyjatekONP jesli nie udalo sie dodac argumentu
     */
    void dodajArgument(double arg) throws WyjatekONP;
}
