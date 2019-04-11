package W4.wyrazenia;

/**
 * Ewaluuje blok wewnątrz dopóki warunek pętli jest >0
 */
public class While extends Wyrazenie {
    protected final Wyrazenie greaterZero;
    protected final Wyrazenie body;

    public While(Wyrazenie greaterZero, Wyrazenie body) {
        if (greaterZero == null)
            throw new IllegalArgumentException("Warunek nie moze byc pusty!");
        if (body == null) body = new Stala(1);

        this.greaterZero = greaterZero;
        this.body = body;
    }

    /**
     * liczy wartość ciała funkcji while dopóki greaterZero > 0
     *
     * @return zwraca ostatnią wartość zwróconą przez body.
     * Jeśli body było null, to zwraca 1 jeśli greaterZero>0, wpp zwraca 0
     */
    @Override
    public double oblicz() {
        double ret = 0;
        while (greaterZero.oblicz() > 0)
            ret = body.oblicz();
        return ret;
    }

    /**
     * @see Wyrazenie
     */
    @Override
    public String toString() {
        return "while(" + greaterZero.toString() + ">0)" +
                "{" + body + "}";
    }
}
