package W4.wyrazenia;

public class Zmienna extends Wyrazenie {
    public static final Zbior zmienne = new Zbior();

    private String zmienna;

    public Zmienna(String name) {
        if (name.trim().length() == 0)
            throw new IllegalArgumentException("Nazwa zmiennej nie może być pusta!");
        zmienna = name;
    }

    /**
     * Dodaje nową zmienną lub przypisuje istniejącej nową wartość
     */
    public static void set(String key, double val) {
        zmienne.ustal(new Para(key, val));
    }

    @Override
    public double oblicz() {
        return zmienne.czytaj(zmienna);
    }

    @Override
    public String toString() {
        return zmienna;
    }
}
