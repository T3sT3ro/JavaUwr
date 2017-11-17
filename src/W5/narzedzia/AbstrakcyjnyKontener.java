package W5.narzedzia;

/**
 * Klasa abstrakcyjna kontenera.
 * Kazda klasa pochodna powinna implementowac metody size i isEmpty
 *
 * @param <T> typ obiektow w kontenerze
 */
public abstract class AbstrakcyjnyKontener<T> {

    /**
     * Zwraca aktualny rozmiar
     * @return size
     */
    public abstract int size();

    /**
     * Sprawdza czy kontener jest pusty
     * @return true jesli size {@literal <} 0
     */
    public abstract boolean isEmpty();
}
