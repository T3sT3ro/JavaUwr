package W3.geometria;

public abstract class Figura implements Cloneable {

    public abstract void przesuń(Wektor v);

    public abstract void obruć(Punkt p, double kąt);

    public abstract void odbij(Prosta p);

    public void obruć(double x, double y, double kąt) {
        this.obruć(new Punkt(x, y), kąt);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
