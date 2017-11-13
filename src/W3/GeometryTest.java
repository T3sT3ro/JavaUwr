package W3;

import W3.geometria.*;

import java.util.Random;
import java.util.Scanner;

public class GeometryTest {

    private static Random g = new Random();

    // random from 0.0f to 100.0f
    private static double r() {
        return g.nextDouble() * 100f;

    }

    public static void main(String[] args) {
        System.out.println("# Program checking geometry library by Tooster");
        System.out.println("# Current error margin - " + Punkt.getErrorMargin());
        System.out.println("# Running tests...");


        objectsInitializationCreationTest();

        objectsRotationTests();

        objectsMirrorTest();

        objectsMoveTest();

        objectsRotationCorrectnessTest();

        objectsMirrorCorrectnessTest();

        objectsMoveCorrectnessTest();

        wektorAddTest();

        prostaMoveTest();

        prostaMethoodsTest();

        System.out.println("TESTING ENDED.\n");
        System.out.println("# Bonus: Interactive tests:");
        System.out.println("#    ^ to draw a point in visualization mode hold 'p' and click on canvas");
        System.out.println("#    ^ to draw a line in visualization mode hold 'l' and click 2 in points on the canvas");
        System.out.println("#    ^ to draw a triangle in visualization mode hold 't' and click in 3 points on the canvas");
        System.out.println("#    ^ to zoom (zooms the center of coordinates system for now) use mouse wheel");
        System.out.println("#    ^ to move canvas SHIFT+drag canvas holding LMB");

        GeometryTestVisualization.visualization();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            System.out.println(scanner.next());
        }
    }

    private static void objectsInitializationCreationTest() {
        try {
            Punkt A = new Punkt(r(), r());
        } catch (IllegalArgumentException e) {
            System.err.println(" FAILED: Couldn't initialize Point");
            e.printStackTrace();
            return;
        }
        try {
            Odcinek o = new Odcinek(
                    new Punkt(r(), r()),
                    new Punkt(r(), r()));
        } catch (IllegalArgumentException e) {
            System.err.println(" FAILED: Couldn't initialize Line");
            e.printStackTrace();
            return;
        }
        try {
            Odcinek o = new Odcinek(0, 0, 0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(" PASSED: Cannot create line from same points");
        }

        try {
            Trojkat o = new Trojkat(
                    new Punkt(r(), r()),
                    new Punkt(r(), r()),
                    new Punkt(r(), r()));
        } catch (IllegalArgumentException e) {
            System.err.println(" FAILED: Couldn't initialize Line");
            e.printStackTrace();
            return;
        }
        try {
            Trojkat o = new Trojkat(0, 0, 0, 0, 0, 0);
        } catch (IllegalArgumentException e) {
            System.out.println(" PASSED: Cannot create triangle from same points");
        }
        try {
            Trojkat o = new Trojkat(0, 0, 1, 1, 2, 2);
        } catch (IllegalArgumentException e) {
            System.out.println(" PASSED: Cannot create triangle from collinear points");
        }
        System.out.println("PASSED: Initialization stage");
    }

    private static void objectsRotationTests() {
        try {
            Punkt p = new Punkt(r(), r());
            p.obroc(r(), r(), g.nextInt());
        } catch (Exception e) {
            System.err.println(" FAILED: Rotation of point");
            e.printStackTrace();
            return;
        }
        try {
            Odcinek o = new Odcinek(
                    r(), r(),
                    r(), r());

            o.obroc(r(), r(), g.nextInt());
        } catch (Exception e) {
            System.err.println(" FAILED: Rotation of line segment");
            e.printStackTrace();
            return;
        }
        try {
            Trojkat t = new Trojkat(
                    r(), r(),
                    r(), r(),
                    r(), r());

            t.obroc(r(), r(), g.nextInt());
        } catch (Exception e) {
            System.err.println(" FAILED: Rotation of triangle");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: Rotation of objects");
    }

    private static void objectsMirrorTest() {
        try {
            Punkt p = new Punkt(r(), r());
            p.odbij(new Prosta(r(), r(), r()));
        } catch (Exception e) {
            System.err.println(" FAILED: Mirror of point");
            e.printStackTrace();
            return;
        }
        try {
            Odcinek o = new Odcinek(r(), r(), r(), r());
            o.odbij(new Prosta(r(), r(), r()));
        } catch (Exception e) {
            System.err.println(" FAILED: Mirror of point");
            e.printStackTrace();
            return;
        }
        try {
            Trojkat t = new Trojkat(r(), r(), r(), r(), r(), r());
            t.odbij(new Prosta(r(), r(), r()));
        } catch (Exception e) {
            System.err.println(" FAILED: Mirror of point");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: Mirror of objects");
    }

    private static void objectsMoveTest() {
        try {
            Punkt p = new Punkt(r(), r());
            p.przesun(new Wektor(r(), r()));
        } catch (Exception e) {
            System.err.println(" FAILED: Move test of Point failed");
            e.printStackTrace();
            return;
        }
        try {
            Odcinek o = new Odcinek(r(), r(), r(), r());
            o.przesun(new Wektor(r(), r()));
        } catch (Exception e) {
            System.err.println(" FAILED: Move test of Line failed");
            e.printStackTrace();
            return;
        }
        try {
            Trojkat t = new Trojkat(r(), r(), r(), r(), r(), r());
            t.przesun(new Wektor(r(), r()));
        } catch (Exception e) {
            System.err.println(" FAILED: Move test of Triangle failed");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: Moving objects with vectors");
    }

    private static void objectsRotationCorrectnessTest() {
        try {
            Punkt p = new Punkt(r(), r());
            Punkt p2 = (Punkt) p.clone();
            if (!p2.equals(p)) {
                System.err.println(" FAILED: clone() on Point");
                return;
            }
            Punkt r = new Punkt(r(), r());
            for (int i = 0; i < 360; i++)
                p2.obroc(r, Math.toRadians(1));
            if (!p2.equals(p))
                System.err.println(" FAILED: rotation error for angle in degrees is too big");

            p2 = (Punkt) p.clone();
            for (int i = 0; i < 100; i++)
                p2.obroc(r, Math.PI);
            if (!p2.equals(p))
                System.err.println(" FAILED: rotation error for angle in radians is too big");

        } catch (Exception e) {
            System.err.println(" FAILED: rotation point correctness test");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: rotation correctness tests");
    }

    private static void objectsMirrorCorrectnessTest() {
        try {
            Punkt p = new Punkt(r(), r());
            Punkt p2 = (Punkt) p.clone();
            if (!p2.equals(p)) {
                System.err.println(" FAILED: clone() on Point");
                return;
            }
            Punkt r = new Punkt(r(), r());
            double a = r(), b = r(), c = r();
            for (int i = 0; i < 100; i++)
                p2.odbij(new Prosta(a, b, c));
            if (!p2.equals(p))
                System.err.println(" FAILED: mirror error is too big");
            a = 1;
            b = 0;
            c = 0;
            p2.odbij(new Prosta(a, b, c));
            p2.odbij(new Prosta(a, b, c));
            if (!p2.equals(p))
                System.err.println(" FAILED: vertical mirror failed");
            a = 0;
            b = 1;
            c = 0;
            p2.odbij(new Prosta(a, b, c));
            p2.odbij(new Prosta(a, b, c));
            if (!p2.equals(p))
                System.err.println(" FAILED: horizontal mirror failed");
        } catch (Exception e) {
            System.err.println(" FAILED: mirror point correctness test");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: mirror correctness tests");
    }

    private static void objectsMoveCorrectnessTest() {
        try {
            Punkt p = new Punkt(r(), r());
            Punkt p2 = (Punkt) p.clone();
            if (!p2.equals(p)) {
                System.err.println(" FAILED: clone() on Point");
                return;
            }
            double dx = r(), dy = r();
            for (int i = 0; i < 100; i++) {
                p2.przesun(new Wektor(dx, dy));
                p2.przesun(Wektor.multiply(new Wektor(dx, dy), -1.0f));
            }
            if (!p2.equals(p))
                System.err.println(" FAILED: move error too big");

            p2 = (Punkt) p.clone();
            p2.przesun(new Wektor(1, 1));
            if (!p2.equals(new Punkt(p.getX() + 1.0f, p.getY() + 1.0f)))
                System.err.println(" FAILED: move doesn't work");

        } catch (Exception e) {
            System.err.println(" FAILED: move point correctness test");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: move correctness tests");
    }

    private static void wektorAddTest() {
        try {
            double dx = r(), dy = r();
            Wektor u = new Wektor(dx, dy);
            Wektor v = new Wektor(dx / 2f, dy / 2f);
            Wektor w = Wektor.add(v, v);
            Wektor x = new Wektor(2f * dx, 2f * dy);
            Wektor z = Wektor.add(x, Wektor.multiply(u, -1f));
            if (!u.equals(w) || !u.equals(z)) {
                System.err.println(" FAILED: adding vectors doesn't work");
            }
        } catch (Exception e) {
            System.err.println(" FAILED: adding vectors test");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: adding vectors tests");
    }

    private static void prostaMoveTest() {
        try {
            Prosta p = new Prosta(r(), r(), r());
            Wektor w = new Wektor(r(), r());
            Prosta p2 = Prosta.przesun(p, w);
            Prosta p3 = Prosta.przesun(p2, Wektor.multiply(w, -1f));
            if (!p3.equals(p)) {
                System.err.println(" FAILED: moving lines doesn't work");
            }
        } catch (Exception e) {
            System.err.println(" FAILED: moving lines test");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: adding vectors tests");
    }

    private static void prostaMethoodsTest() {
        try {
            Prosta p = new Prosta(r(), r(), r());
            Prosta p_para = new Prosta(p.a, p.b, r());
            Prosta p_perp = new Prosta(p.b, p.a, r());
            if (!Prosta.isParallel(p, p_para))
                System.err.println(" FAILED: parallel checking not working");
            else
                System.out.println(" PASSED: parallel checking");
            if (!Prosta.isPerpendicular(p, p_perp))
                System.err.println(" FAILED: perpendicular checking not working");
            else
                System.out.println(" PASSED: perpendicular checking");
            Prosta x = new Prosta(1, -1, -1);
            Prosta y = new Prosta(1, 1, 0);
            if (!Prosta.getIntersection(x, y).equals(new Punkt(0.5f, -0.5f)))
                System.err.println(" FAILED: intersection not working");
            else if (Prosta.getIntersection(x, y) == null)
                System.out.println(" INFO: intersection doesn't exist or is out of bounds");
            else
                System.out.println(" PASSED: intersection checking");
        } catch (Exception e) {
            System.err.println(" FAILED: parallel or perpendicular or intersection method test");
            e.printStackTrace();
            return;
        }
        System.out.println("PASSED: Prosta methods tests");
    }
}
