package W3.geometria;

/**
 * Class representing a triangle with points A, B, C in 2D space.
 * If two triangles have the same points but in different order,
 * they are seen as different triangles
 * e.g. trainagle {A,B,C} and [B,C,A) are not the same triangles
 */
public class Trojkat extends Figura {
    public Punkt A, B, C;

    public Trojkat(Punkt a, Punkt b, Punkt c) {
        A = a;
        B = b;
        C = c;
        validate();
    }

    public Trojkat(double x1, double y1, double x2, double y2, double x3, double y3) {
        this(new Punkt(x1, y1), new Punkt(x2, y2), new Punkt(x3, y3));
    }

    private void validate() throws IllegalArgumentException {
        if (A == null || B == null || C == null)
            throw new IllegalArgumentException("Endpoint cannot be null!");

        // Xb*Yc-Xc*Yb == 0 moved by vector a
        boolean collinear =
                Math.abs((B.getX() - A.getX()) * (C.getY() - A.getY())
                        - (B.getY() - A.getY()) * (C.getX() - A.getX())) < Punkt.EPSILON;
        if (A.equals(B)
                || B.equals(C)
                || C.equals(A)
                || collinear)
            throw new IllegalArgumentException("Points cannot be collinear!");

    }


    @Override
    public void przesuń(Wektor v) {
        A.przesuń(v);
        B.przesuń(v);
        C.przesuń(v);
    }

    @Override
    public void obruć(Punkt p, double kąt) {
        A.obruć(p, kąt);
        B.obruć(p, kąt);
        C.obruć(p, kąt);
    }

    @Override
    public void odbij(Prosta p) {
        A.odbij(p);
        B.odbij(p);
        C.odbij(p);
    }

    @Override
    public Trojkat clone() throws CloneNotSupportedException {
        return new Trojkat((Punkt) this.A.clone(), (Punkt) this.B.clone(), (Punkt) this.C.clone());
    }
}
