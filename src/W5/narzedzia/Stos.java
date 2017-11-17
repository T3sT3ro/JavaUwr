package W5.narzedzia;

import W5.narzedzia.wyjatki.WyjatekKontenera;

/**
 * Stos zaimplementowany na liscie dwukierunkowej
 *
 * @param <T> typ obiektow
 * @see AbstrakcyjnyKontener
 */
public class Stos<T> extends AbstrakcyjnyKontener<T> {
    final Lista<T> list;

    public Stos() {
        list = new Lista<>();
    }

    /**
     * Dodaje element na szczyt stosu
     * @param val nowy element
     * @throws WyjatekKontenera {@link Lista}
     */
    public void add(T val) throws WyjatekKontenera {
        list.addLast(val);
    }

    /**
     * Zwraca element ze szczytu stosu
     * @return element ze szczytu stosu
     * @throws WyjatekKontenera {@link Lista}
     */
    public T get() throws WyjatekKontenera {
        return list.getLast();
    }

    /**
     * Usuwa element ze szczytu stosu
     * @throws WyjatekKontenera {@link Lista}
     */
    public void remove() throws WyjatekKontenera {
        list.removeLast();
    }

    /**
     * Usuwa i zwraca element ze szczytu stosu
     * @return {@link Lista}
     * @throws WyjatekKontenera {@link Lista}
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
