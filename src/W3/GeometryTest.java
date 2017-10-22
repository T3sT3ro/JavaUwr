package W3;

import W3.geometria.Figura;
import W3.geometria.Odcinek;
import W3.geometria.Punkt;
import W3.geometria.Trojkat;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GeometryTest extends JPanel {

    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color AXIS_MAIN_COLOR = Color.BLACK;
    private static final Stroke AXIS_MAIN_STROKE = new BasicStroke(1f);
    private static final Color AXIS_GUIDE_COLOR = Color.LIGHT_GRAY;
    private static final Stroke AXIS_GUIDE_STROKE = new BasicStroke(1f);
    private static final Color POINT_COLOR = Color.RED;
    private static final Stroke POINT_STROKE = new BasicStroke(2f);
    private static final int POINT_RADIUS = 6;
    private static final Color ACTIVE_COLOR = Color.ORANGE;
    private static final Stroke ACTIVE_STROKE = new BasicStroke(2f);
    private static final int GUIDE_DISTANCE = 1;

    private static final int width = 1200;
    private static final int height = 800;
    private static ArrayList<Figura> figury = new ArrayList<Figura>();
    private static Figura active;
    Timer timer;
    private double zoom = 50;
    private int offsetX;
    private int offsetY;

    public GeometryTest() {
        setPreferredSize(new Dimension(width, height));

        offsetX = width / 2;
        offsetY = height / 2;
        figury.add(new Punkt(7, 7));
        figury.add(new Odcinek(2, 2, 3, 3));
        figury.add(new Trojkat(0, 0, -1.3, -1.5, 0, -1));
        try {
            Trojkat tr = (Trojkat) figury.get(2).clone();
            tr.obruć(0, 0, Math.toRadians(30));
            figury.add(tr);
        } catch (CloneNotSupportedException e) {
            System.err.println("Couldn't clone a triangle");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new GeometryTest());
                frame.setResizable(false);
                frame.setSize(width + 100, height + 100);
                frame.pack();

                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }

    public void drawAxis(Graphics2D g) {
        g.setColor(AXIS_GUIDE_COLOR);
        g.setStroke(AXIS_GUIDE_STROKE);
        for (int i = GUIDE_DISTANCE; i * zoom <= offsetX; i += GUIDE_DISTANCE) {
            g.drawLine(i * (int) zoom, -offsetY * (int) zoom, i * (int) zoom, offsetY * (int) zoom);
            g.drawLine(-i * (int) zoom, -offsetY * (int) zoom, -i * (int) zoom, offsetY * (int) zoom);
            g.drawLine(-offsetX * (int) zoom, i * (int) zoom, offsetX * (int) zoom, i * (int) zoom);
            g.drawLine(-offsetX * (int) zoom, -i * (int) zoom, offsetX * (int) zoom, -i * (int) zoom);
        }

        g.setColor(AXIS_MAIN_COLOR);
        g.setStroke(AXIS_MAIN_STROKE);
        Line2D OX = new Line2D.Double(-offsetX * zoom, 0 * zoom, offsetX * zoom, 0 * zoom);
        Line2D OY = new Line2D.Double(0 * zoom, -offsetY * zoom, 0 * zoom, offsetY * zoom);
        g.draw(OX);
        g.draw(OY);
    }

    private void drawPunkt(Graphics2D g, Punkt p) {
        g.setColor(POINT_COLOR);
        g.setStroke(POINT_STROKE);
        g.fillOval((int) (p.getX() * zoom) - POINT_RADIUS / 2, (int) ((-1) * p.getY() * zoom) - POINT_RADIUS / 2, POINT_RADIUS, POINT_RADIUS);

    }

    private void drawOdcinek(Graphics2D g, Odcinek o) {
        g.setColor(ACTIVE_COLOR);
        g.setStroke(ACTIVE_STROKE);
        g.drawLine((int) (o.A.getX() * zoom), (int) (o.A.getY() * (-1) * zoom), (int) (o.B.getX() * zoom), (int) (o.B.getY() * (-1) * zoom));
        drawPunkt(g, o.A);
        drawPunkt(g, o.B);
    }

    public void drawTrojkat(Graphics2D g, Trojkat t) {
        g.setColor(ACTIVE_COLOR);
        g.setStroke(ACTIVE_STROKE);
        drawOdcinek(g, new Odcinek(t.A, t.B));
        drawOdcinek(g, new Odcinek(t.B, t.C));
        drawOdcinek(g, new Odcinek(t.C, t.A));
        drawPunkt(g, t.A);
        drawPunkt(g, t.B);
        drawPunkt(g, t.C);
    }

    public void drawElements(Graphics2D g) {
        for (Figura element : figury) {
            if (element instanceof Punkt) {
                drawPunkt(g, (Punkt) element);
            } else if (element instanceof Odcinek) {
                drawOdcinek(g, (Odcinek) element);
            } else if (element instanceof Trojkat) {
                drawTrojkat(g, (Trojkat) element);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g.translate(offsetX, offsetY); // translate origin to offsetX , offsetY
        drawAxis(g2d);
        drawElements(g2d);
    }
}
