package W5.narzedzia;

import W5.narzedzia.wyjatki.WyjatekKontenera;

public class Kolejka<T> extends AbstrakcyjnyKontener<T> {

    final Lista<T> list;

    public Kolejka() {
        list = new Lista<>();
    }

    public void add(T val) throws WyjatekKontenera {
        list.addLast(val);
    }

    public T get() throws WyjatekKontenera {
        return list.getFirst();
    }

    public void remove() throws WyjatekKontenera {
        list.removeFirst();
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
