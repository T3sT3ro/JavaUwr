package W3.geometria;

public class Prosta {
    /// Ax + By + C = 0
    public final double a, b, c;

    public Prosta(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static Prosta przesun(Prosta p, Wektor v) {
        return new Prosta(p.a, p.b, p.c - (p.a * v.dx) - (p.b * v.dy));
    }

    public static boolean isParallel(Prosta p, Prosta q) {
        return Math.abs(p.a * q.b - q.a * p.b) < Punkt.EPSILON;
    }

    public static boolean isPerpendicular(Prosta p, Prosta q) {
        return Math.abs((p.a / p.b) * (q.a / q.b) - 1.0f) < Punkt.EPSILON;
    }

    /**
     * Return intersection point of two lines, or null if:
     * - lines are parallel
     * - intersection point is out of double limits
     * - lines cover each other (with precision 'EPSILON')
     *
     * @param p first line
     * @param q second line
     * @return point of intersection, null if point cannot be calculated
     */
    public static Punkt getIntersection(Prosta p, Prosta q) {
        if (isParallel(p, q))
            return null;
        // determinants
        Double A1 = p.a, A2 = q.a, B1 = p.b, B2 = q.b, C1 = p.c, C2 = q.c, W, Wx, Wy;
        W = A1 * B2 - A2 * B1;
        Wx = (-C1) * B2 + C2 * B1;
        Wy = A1 * (-C2) + A2 * C1;
        Double _x = Wx / W, _y = Wy / W;
        if (_x.isInfinite() || _x.isNaN() || _y.isInfinite() || _y.isNaN())
            return null;
        return new Punkt(_x, _y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prosta p = (Prosta) o;

        return (Math.abs(a - p.a) < Punkt.EPSILON)
                && (Math.abs(b - p.b) < Punkt.EPSILON)
                && (Math.abs(c - p.c) < Punkt.EPSILON);
    }
}
