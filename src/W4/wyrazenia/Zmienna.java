package W4.wyrazenia;

/**
 * Klasa reprezentujaca zmienna
 */
public class Zmienna extends Wyrazenie {
    /**
     * Zbior wszystkich zdefiniowanych zmiennych
     */
    public static final Zbior zmienne = new Zbior();

    /**
     * Nazwa zmiennej
     */
    private String zmienna;

    /**
     * Tworzy nowa zmienna o nazwie name, zglasza wyjatek jesli nazwa jest nieprawidlowa.
     *
     * @param name nazwa zmiennej
     */
    public Zmienna(String name) {
        if (name.trim().length() == 0)
            throw new IllegalArgumentException("Nazwa zmiennej nie moze byc pusta!");
        zmienna = name;
    }

    /**
     * Dodaje nowa zmienna lub przypisuje istniejacej nowa wartosc
     * @param key klucz
     * @param val wartosc
     */
    public static void set(String key, double val) {
        zmienne.ustal(new Para(key, val));
    }

    /**
     * Oblicza wartosc zmiennej
     * @return zwraca wartosc zmiennej
     */
    @Override
    public double oblicz() {
        return zmienne.czytaj(zmienna);
    }

    @Override
    public String toString() {
        return zmienna;
    }
}
