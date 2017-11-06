package W3.geometria;

/**
 * Class representing a point in 2D euclidean space
 * WARNING! precision is set to the value of EPSILON
 */
public class Punkt extends Figura implements Cloneable {
    /**
     * We cannot compare two doubles in a==b way
     * so we check if their difference is smaller then our EPSILON
     * (thus numbers are approximately equal)
     */
    protected static final double EPSILON = 1e-10;

    private double x, y;

    public Punkt(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setLocation(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static final double getErrorMargin() {
        return EPSILON;
    }

    /**
     * Compare points and return true if p has x greater, or y greater when x is equal than 'this'
     *
     * @return true if this is less than p
     */
    public boolean lessThan(Punkt p) {
        return x < p.x || (x == p.x && y < p.y);
    }

    @Override
    public void przesun(Wektor v) {
        x += v.dx;
        y += v.dy;
    }

    /**
     * Rotate this point <kat> degrees in radians </kat>around point p
     *
     * @param p   origin of rotation
     * @param kat degree in radians
     */
    @Override
    public void obroc(Punkt p, double kat) {
        x -= p.x;
        y -= p.y;
        // multiplication with rotation matrix
        double _x = x * Math.cos(kat) - y * Math.sin(kat);
        double _y = x * Math.sin(kat) + y * Math.cos(kat);
        x = _x + p.x;
        y = _y + p.y;
    }

    @Override
    public void odbij(Prosta p) {
        Punkt intersection = Prosta.getIntersection(p, new Prosta(-p.b, p.a, p.b * x - p.a * y));
        Punkt p2 = new Punkt(x, y);
        p2.przesun(Wektor.multiply(Wektor.between(p2, intersection), 2.0));
        this.x = p2.getX();
        this.y = p2.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Punkt punkt = (Punkt) o;

        return (Math.abs(x - punkt.x) < EPSILON) && (Math.abs(y - punkt.y) < EPSILON);
    }


}
