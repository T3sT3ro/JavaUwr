package W4.wyrazenia;

//TODO dodać zabezpieczenia na double (dzielenie przez 0, NaN, Infinity) i wyjątki
//TODO modyfikatory dostępu
//TODO doc
//TODO package-info.java

import W4.wyrazenia.unary.*;

public abstract class Wyrazenie {

    public static double sumuj(Wyrazenie... wyr) {
        double wynik = 0f;
        for (Wyrazenie w : wyr) {
            wynik += w.oblicz();
        }
        return wynik;
    }

    public static double pomnoz(Wyrazenie... wyr) {
        double wynik = 1f;
        for (Wyrazenie w : wyr) {
            wynik *= w.oblicz();
        }
        return wynik;
    }

    /**
     * oblicza wyrażenie i zwraca wynik typu double
     */
    public abstract double oblicz() throws ArithmeticException;

    /**
     * zwraca wyrażenie w nawiasach jeśli to potrzebne i bez w przeciwnym wypadku
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
                        this instanceof binary.Max ||
                        this instanceof binary.Min ||
                        this instanceof binary.Logarytm
                )
            return this.toString();
        return "(" + this.toString() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        return Double.compare(this.oblicz(), ((Wyrazenie) obj).oblicz()) == 0;
    }
}
