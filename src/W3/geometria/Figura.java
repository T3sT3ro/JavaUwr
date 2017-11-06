package W3.geometria;

public abstract class Figura implements Cloneable {

    public abstract void przesun(Wektor v);

    public abstract void obroc(Punkt p, double kat);

    public abstract void odbij(Prosta p);

    public void obroc(double x, double y, double kat) {
        this.obroc(new Punkt(x, y), kat);
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
