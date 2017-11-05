package W4.wyrazenia;

import java.util.Arrays;

public class Zbior {

    private static final int INITIAL_SIZE = 1 << 10;
    private Para array[];
    private int size = 0;

    public Zbior() {
        this(INITIAL_SIZE);
    }

    public Zbior(int size) {
        this.array = new Para[size];
    }

    private void resizeOnOverflow() {
        if (size == array.length) {
            array = Arrays.copyOf(array, array.length * 2);
        }
    }

    /**
     * szuka pary z kluczem
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
     * wstawia nową parę do zbioru
     */
    public void wstaw(Para p) throws IllegalArgumentException {
        if (szukaj(p.klucz) != null)
            throw new IllegalArgumentException("Pair already exists in a set!");
        resizeOnOverflow();
        array[size++] = p;
    }

    /**
     * odszukuje parę z kluczem i zwraca wartość
     */
    public double czytaj(String kl) throws IllegalArgumentException {
        Para found = szukaj(kl);
        if (found == null)
            throw new IllegalArgumentException("Given pair doesn't exist in this set!");
        return found.getValue();
    }

    /**
     * wstawia nową albo aktualizuje istniejącą parę
     */
    public void ustal(Para p) throws IllegalArgumentException {
        Para found = szukaj(p.klucz);
        if (found != null)
            found.setValue(p.getValue());
        else
            wstaw(p);
    }

    /**
     * podaje ile par jest w zbiorze
     */
    public int ile() {
        return size;
    }

    /**
     * czyści zbiór
     */
    public void czysc() {
        Arrays.fill(array, null);
        size = 0;
    }

}
