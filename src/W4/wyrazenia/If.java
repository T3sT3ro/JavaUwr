package W4.wyrazenia;

public class If extends Wyrazenie {
    protected final Wyrazenie greaterZero;
    protected final Wyrazenie trueClause, falseClause;

    /**
     * @param greaterZero warunek instrukcji if
     * @param trueClause  wyrażenie obliczane jeśli warunek jest prawdziwy; dla trueClause == null zwraca 1
     * @param falseClause wyrażenie obliczane jeśli warunek jest faszywy; dla falseClause == null zwraca 0
     */
    public If(Wyrazenie greaterZero, Wyrazenie trueClause, Wyrazenie falseClause) {
        if (greaterZero == null)
            throw new IllegalArgumentException("Warunek nie moze byc pusty!");

        if (trueClause == null) trueClause = new Stala(1);
        if (falseClause == null) falseClause = new Stala(0);

        this.trueClause = trueClause;
        this.greaterZero = greaterZero;
        this.falseClause = falseClause;
    }

    @Override
    public double oblicz() {
        return greaterZero.oblicz() > 0 ? trueClause.oblicz() : falseClause.oblicz();
    }

    @Override
    public String toString() {
        return "if(" + greaterZero.toStringFormat() + ">0)" +
                "T:(" + trueClause.toStringFormat() + ")" +
                "N:(" + falseClause.toStringFormat() + ")";
    }
}
