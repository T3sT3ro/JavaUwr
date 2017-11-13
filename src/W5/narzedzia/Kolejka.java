package W5.narzedzia;

import W5.narzedzia.wyjatki.WyjatekKontenera;

/**
 * Kolejka zaimplementowana na Liscie dwukierunkowej
 *
 * @param <T>
 * @see Lista
 */
public class Kolejka<T> extends AbstrakcyjnyKontener<T> {

    final Lista<T> list;

    public Kolejka() {
        list = new Lista<>();
    }

    /**
     * Dodaje na koncu kolejki obiekt val
     * @param val obiekt do dodania
     * @throws WyjatekKontenera
     */
    public void add(T val) throws WyjatekKontenera {
        list.addLast(val);
    }

    /**
     * Zwraca element na poczatku kolejki
     * @return
     * @throws WyjatekKontenera
     */
    public T get() throws WyjatekKontenera {
        return list.getFirst();
    }

    /**
     * Usuwa element z poczatku kolejki
     * @throws WyjatekKontenera
     */
    public void remove() throws WyjatekKontenera {
        list.removeFirst();
    }

    /**
     * Usuwa element z poczatku kolejki i zwraca go
     * @return
     * @throws WyjatekKontenera
     */
    public T pop() throws WyjatekKontenera {
        T ret = this.get();
        this.remove();
        return ret;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
}
