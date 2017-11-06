package W4.wyrazenia;



public class Mnozenie extends Binary {
    public Mnozenie(Wyrazenie w, Wyrazenie x) {
        super(w, x);
    }

    @Override
    public double oblicz() {
        return super.w.oblicz() * super.x.oblicz();
    }

    @Override
    public String toString() {
        return super.w.toStringFormat() + "*" + super.x.toStringFormat();
    }
}
