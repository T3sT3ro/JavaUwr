package W4.wyrazenia;

/**
 * Ewaluuje blok i zwraca wynik drugiego operandu
 */
public class CodeBlock extends Wyrazenie {
    protected final Wyrazenie[] w;

    public CodeBlock(Wyrazenie... w) {
        this.w = new Wyrazenie[w.length];
        for (int i = 0; i < w.length; i++)
            this.w[i] = w[i];
    }

    /**
     * @see Wyrazenie
     */
    @Override
    public double oblicz() {
        double ret = 0;
        for (Wyrazenie x : w)
            ret = x.oblicz();
        return ret;
    }

    /**
     * @see Wyrazenie
     */
    @Override
    public String toString() {
        String[] subBlock = new String[w.length];
        for (int i = 0; i < subBlock.length; i++)
            subBlock[i] = w[i].toStringFormat() + ";";
        return "{" + String.join("", subBlock)+ "}";
    }
}
