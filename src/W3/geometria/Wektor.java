package W3.geometria;

public class Wektor {
    public final double dx, dy;

    public Wektor(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public Wektor(Punkt p) {
        dx = p.getX();
        dy = p.getY();
    }


    /**
     * Static method to add two vectors.
     *
     * @param a first vector
     * @param b second vector
     * @return new vector as a result of adding a and b
     */
    public static Wektor add(Wektor a, Wektor b) {
        return new Wektor(a.dx + b.dx, a.dy + b.dy);
    }

    /**
     * Returns vector multiplied by scalar x
     *
     * @param v base vector
     * @param x scalar
     * @return vector v multiplied by scalar x,
     */
    public static Wektor multiply(Wektor v, double x) {
        return new Wektor(v.dx * (x), v.dy * (x));
    }

    /**
     * Returns vector a-&gt;b
     *
     * @param a starting point
     * @param b ending point
     * @return vector a-&gt;b
     */
    public static Wektor between(Punkt a, Punkt b) {
        return new Wektor(b.getX() - a.getX(), b.getY() - a.getY());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Wektor wektor = (Wektor) o;

        return (Math.abs(dx - wektor.dx) < Punkt.EPSILON) && (Math.abs(dy - wektor.dy) < Punkt.EPSILON);
    }

}
