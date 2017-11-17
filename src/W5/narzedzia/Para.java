package W5.narzedzia;

/**
 * Klasa para reprezentujaca pary {klucz, wartosc}
 *
 * @param <K> typ klucza
 * @param <V> typ wartosci
 */
public class Para<K, V> {
    private final K key;
    private V value;

    public Para(K key, V value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[" +
                key +
                ", " + value +
                ']';
    }

    /**
     * Jesli obiekty maja takie same klucze, zwraca true
     * @param obj obiekt do porownania
     * @return true jestli obj.klucz.equals(this.klucz)
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof Para && this.key.equals(((Para) obj).key);
    }

    /**
     * Zwraca wartosc
     * @return V.value
     */
    public V getValue() {
        return value;
    }

    /**
     * Ustawia wartosc
     * @param value nowa wartosc
     */
    public void setValue(V value) {
        this.value = value;
    }
}
