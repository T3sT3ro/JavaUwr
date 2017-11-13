package W5.narzedzia;

import W5.narzedzia.wyjatki.WyjatekKontenera;

public class Stos<T> extends AbstrakcyjnyKontener<T> {
    final Lista<T> list;

    public Stos() {
        list = new Lista<>();
    }

    public void add(T val) throws WyjatekKontenera {
        list.addLast(val);
    }

    public T get() throws WyjatekKontenera {
        return list.getLast();
    }

    public void remove() throws WyjatekKontenera {
        list.removeLast();
    }

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
