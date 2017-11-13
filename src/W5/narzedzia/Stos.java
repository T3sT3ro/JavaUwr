package W5.narzedzia;

import W5.narzedzia.wyjatki.WyjatekKontenera;

/**
 * Stos zaimplementowany na liscie dwukierunkowej
 *
 * @param <T>
 * @see AbstrakcyjnyKontener
 */
public class Stos<T> extends AbstrakcyjnyKontener<T> {
    final Lista<T> list;

    public Stos() {
        list = new Lista<>();
    }

    /**
     * Dodaje element na szczyt stosu
     * @param val
     * @throws WyjatekKontenera
     */
    public void add(T val) throws WyjatekKontenera {
        list.addLast(val);
    }

    /**
     * Zwraca element ze szczytu stosu
     * @return
     * @throws WyjatekKontenera
     */
    public T get() throws WyjatekKontenera {
        return list.getLast();
    }

    /**
     * Usuwa element ze szczytu stosu
     * @throws WyjatekKontenera
     */
    public void remove() throws WyjatekKontenera {
        list.removeLast();
    }

    /**
     * Usuwa i zwraca element ze szczytu stosu
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
