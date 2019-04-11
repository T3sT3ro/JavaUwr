package W4.wyrazenia;

public class Przypisz extends Wyrazenie {
    Zmienna x;
    Wyrazenie w;

    public Przypisz(Zmienna x, Wyrazenie w) {
        if (x == null || w == null)
            throw new IllegalArgumentException("W przypisaniu zmienna i wyrażenie nie mogą być puste.");
        this.x = x;
        this.w = w;
    }

    @Override
    public double oblicz() {
        double val = w.oblicz();
        Zmienna.set(x.getZmienna(), val);
        return val;
    }

    @Override
    public String toString() {
        return x.toStringFormat() + ":=" + w.toStringFormat();
    }
}
