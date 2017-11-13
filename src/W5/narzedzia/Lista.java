package W5.narzedzia;

import W5.narzedzia.wyjatki.Kontener_NieistniejacyElement;
import W5.narzedzia.wyjatki.Kontener_PustyKontener;
import W5.narzedzia.wyjatki.WyjatekKontenera;

public class Lista<T> extends AbstrakcyjnyKontener<T> {

    Node begin;
    Node end;
    private int size = 0;

    public Lista() {
        begin = new Node();
        end = new Node();
        begin.prev = begin;
        begin.next = end;
        end.prev = begin;
        end.next = end;
        begin.val = null;
        end.val = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(T val) throws WyjatekKontenera {
        if (val == null)
            throw new IllegalArgumentException("cannot insert null to list");
        Node x = new Node(val);
        x.next = begin.next;
        x.prev = begin;
        begin.next.prev = x;
        begin.next = x;
        size++;

    }

    public void addLast(T val) throws WyjatekKontenera {
        if (val == null)
            throw new IllegalArgumentException("cannot insert null into the list");
        Node x = new Node(val);
        x.next = end;
        x.prev = end.prev;
        end.prev.next = x;
        end.prev = x;
        size++;
    }

    public T getFirst() throws WyjatekKontenera {
        if (this.isEmpty())
            throw new Kontener_PustyKontener("kontener jest pusty");
        return begin.next.val;
    }

    public T getLast() throws WyjatekKontenera {
        if (this.isEmpty())
            throw new Kontener_PustyKontener("kontener jest pusty");
        return end.prev.val;
    }

    public void removeFirst() throws WyjatekKontenera {
        if (this.isEmpty())
            throw new Kontener_PustyKontener("kontener jest pusty");

        begin.next.next.prev = begin;
        begin.next = begin.next.next;
        size--;
    }

    public void removeLast() throws WyjatekKontenera {
        if (this.isEmpty())
            throw new Kontener_PustyKontener("kontener jest pusty");
        end.prev.prev.next = end;
        end.prev = end.prev.prev;
        size--;
    }

    public void remove(T element) throws WyjatekKontenera {
        Node found = findNode(element);
        if (found == null)
            throw new Kontener_NieistniejacyElement("nie ma takiego elementu w kontenerze");
        if (this.isEmpty())
            throw new Kontener_PustyKontener("kontener jest pusty");
        found.prev.next = found.next;
        found.next.prev = found.prev;
    }

    public T find(T element) throws WyjatekKontenera {
        if (element == null) throw new WyjatekKontenera("nie mozna wyszukac elementu null");
        Node current = begin.next;
        while (current != end) {
            if (current.val == null)
                throw new WyjatekKontenera("element kontenera ma val=null");
            if (current.val.equals(element))
                return current.val;
            current = current.next;
        }
        return null;
    }

    public void clear() {
        begin.next = end;
        end.prev = begin;
        size = 0;
    }

    private Node findNode(T element) {
        if (element == null)
            return null;
        Node current = begin.next;
        while (current != end) {
            if (current.val.equals(element))
                return current;
            current = current.next;
        }
        return null;
    }

    private class Node {
        Node prev;
        Node next;
        T val;

        private Node() {
            prev = this;
            next = this;
            val = null;
        }

        private Node(T val) {
            prev = this;
            next = this;
            this.val = val;
        }

    }
}
