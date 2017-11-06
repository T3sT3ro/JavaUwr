package W4.wyrazenia;


/**
 * Klasa abstrakcyjna reprezentujaca Drzewo Wyrazen.
 * Moze skladac sie z innych wyrazen, stalych i zmiennych
 */
public abstract class Wyrazenie {

    /**
     * Metoda zwracajaca wynik sumy wyrazen
     * Zglasza wyjatek jesli ktorekolwiej wyrazenie jest null
     *
     * @param wyr kolejne wyrazenia do sumowania
     * @return suma wynikow wyrazen
     */
    public static double sumuj(Wyrazenie... wyr) {
        double wynik = 0f;
        for (Wyrazenie w : wyr) {
            if (w == null)
                throw new IllegalArgumentException("Wyrazenie nie moze byc puste!");
            wynik += w.oblicz();
        }
        return wynik;
    }

    /**
     * Metoda zwracajaca wynik iloczynu wyrazen
     * Zglasza wyjatek jesli ktorekolwiej wyrazenie jest null
     *
     * @param wyr kolejne wyrazenia do wymnozenia
     * @return iloczyn wynikow wyrazen
     */
    public static double pomnoz(Wyrazenie... wyr) {
        double wynik = 1f;
        for (Wyrazenie w : wyr) {
            if (w == null)
                throw new IllegalArgumentException("Wyrazenie nie moze byc puste!");
            wynik *= w.oblicz();
        }
        return wynik;
    }

    /**
     * Oblicza wynik wyrazenia typu double
     */
    public abstract double oblicz();

    /**
     * Zwraca napis reprezentujacy wyrazenie w nawiasach jesli jest to potrzebne
     */
    protected String toStringFormat() {
        if (
                this instanceof Zmienna ||
                        this instanceof Stala ||
                        this instanceof Cosinus ||
                        this instanceof Sinus ||
                        this instanceof Arctan ||
                        this instanceof Przeciwienstwo ||
                        this instanceof Abs ||
                        this instanceof Max ||
                        this instanceof Min ||
                        this instanceof Logarytm
                )
            return this.toString();
        return "(" + this.toString() + ")";
    }

    /**
     * Zwraca napis reprezentujacy wyrazenie
     */
    public abstract String toString();

    /**
     * Porownuje wartosci dwoch wyrazen za pomoca Double.compare()
     *
     * @param obj obiekt do porownania
     * @return true jesli Double.compare zwraca 0
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Wyrazenie)) return false;
        return Double.compare(this.oblicz(), ((Wyrazenie) obj).oblicz()) == 0;
    }
}
