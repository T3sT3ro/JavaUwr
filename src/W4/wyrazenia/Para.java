package W4.wyrazenia;

/**
 * Klasa reprezentujaca pary [klucz, wartosc]
 */
public class Para {
    public final String klucz;
    private double wartosc;

    /**
     * Konstruktor tworzacy nowa pare [klucz, wartosc]
     *
     * @param klucz
     * @param wartosc
     */
    public Para(String klucz, double wartosc) {
        this.klucz = klucz;
        this.wartosc = wartosc;
    }

    /**
     * Zwraca wartosc skojarzona z kluczem
     */
    public double getValue() {
        return wartosc;
    }

    /**
     * ustawia wartosc skojarzona z kluczem
     */
    public void setValue(double wartosc) {
        this.wartosc = wartosc;
    }

    /**
     * Zwraca tekst reprezentujacy pare ['klucz', wartosc]
     */
    @Override
    public String toString() {
        return "[ '" + klucz + "', " + wartosc + " ]";
    }


    /**
     * Porownuje dwie pary na podstawie ich kluczy
     *
     * @param obj obiekt do porownania
     * @return true jesli this i obj maja takie same klucze
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Para)) return false;

        Para p = (Para) obj;
        return klucz.equals(p.klucz);
    }
}
