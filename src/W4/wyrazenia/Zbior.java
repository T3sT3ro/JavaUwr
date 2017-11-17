package W4.wyrazenia;

import java.util.Arrays;

/**
 * Klasa bedaca kolekcja obiektow typu Para.
 * Domyslna pojemnosc wynosi 128 elementow;
 * jesli zabraknie miejsca, zaalokuje wiecej miejsca
 */
public class Zbior {

    private static final int INITIAL_SIZE = 128;
    private Para array[];
    private int size = 0;

    /**
     * Konstruktor domyslny, tworzy kontener o pojemnosci INITIAL_SIZE
     */
    public Zbior() {
        this(INITIAL_SIZE);
    }

    /**
     * Konstruktor tworzacy zbior o zadanym poczatkowym rozmiarze
     *
     * @param size poczatkowy rozmiar
     */
    public Zbior(int size) {
        this.array = new Para[size];
    }

    /**
     * Metoda alokujaca tablice dwukrotnie wieksza od obecnej jesli zabraknie miejsca
     */
    private void resizeOnOverflow() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    /**
     * Wyszukuje pare o kluczu kl
     *
     * @param kl klucz
     * @return Para p o kluczu kl, null jesli para nie istnieje w zbiorze
     */
    public Para szukaj(String kl) {
        for (Para p : array) {
            if (p == null) return null;
            if (p.klucz.equals(kl))
                return p;
        }
        return null;
    }

    /**
     * Wstawia nowa pare p do zbioru, jesli ta juz istnieje, zglasza wyjatek.
     *
     * @param p para
     */
    public void wstaw(Para p) throws IllegalArgumentException {
        if (szukaj(p.klucz) != null)
            throw new IllegalArgumentException("Pair already exists in a set!");
        resizeOnOverflow();
        array[size++] = p;
    }

    /**
     * Zwraca wartosc elementu o kluczu kl, zglasza wyjatek jesli nie ma takiego elementu
     *
     * @param kl klucz
     * @return para o podaym kluczu
     */
    public double czytaj(String kl) throws IllegalArgumentException {
        Para found = szukaj(kl);
        if (found == null)
            throw new IllegalArgumentException("Given pair doesn't exist in this set!");
        return found.getValue();
    }

    /**
     * Jesli para istnieje aktualizuje ja, jesli nie, wstawia do zbioru
     *
     * @param p para
     * @throws IllegalArgumentException jesli Para jest nieprawidlowa
     */
    public void ustal(Para p) throws IllegalArgumentException {
        Para found = szukaj(p.klucz);
        if (found != null)
            found.setValue(p.getValue());
        else
            wstaw(p);
    }

    /**
     * Podaje ile jest par w zbiorze
     *
     * @return size
     */
    public int ile() {
        return size;
    }

    /**
     * Czysci zbior
     */
    public void czysc() {
        Arrays.fill(array, null);
        size = 0;
    }

}
