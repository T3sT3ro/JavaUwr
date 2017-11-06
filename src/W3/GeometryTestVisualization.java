package W3;

import W3.geometria.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class GeometryTestVisualization extends JPanel {

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
    public static ArrayList<Figura> figury = new ArrayList<>();
    private static Figura active;
    Timer timer;
    private double zoom;
    private double zoomBase = 5;
    private double zoomPow = 2;
    private Point offset = new Point(0, 0);
    private Point oldOffset = new Point(0, 0);
    private Point startingLocation;
    private boolean DRAWING_POINT = false;
    private boolean DRAWING_LINE = false;
    private int DRAWING_LINE_POINT = 0;
    private Punkt DRAWING_LINE_A;
    private Punkt DRAWING_LINE_B;
    private boolean DRAWING_TRIANGLE = false;
    private int DRAWING_TRIANGLE_POINT = 0;
    private Punkt DRAWING_TRIANGLE_A;
    private Punkt DRAWING_TRIANGLE_B;
    private Punkt DRAWING_TRIANGLE_C;

    private GeometryTestVisualization() {
        setPreferredSize(new Dimension(width, height));

        addListeners();

        offset.x = width / 2;
        offset.y = height / 2;
        zoom = Math.pow(zoomBase, zoomPow);

        try {
            Trojkat t = new Trojkat(4, 4, 0.1, -1.1, -3, -0.1);
            t.przesun(new Wektor(1, 5));
            Trojkat t2 = t.clone();
            t2.odbij(new Prosta(0, 1, 0));
            Trojkat t3 = t.clone();
            t3.odbij(new Prosta(1, 0, 3));
            Trojkat t4 = t.clone();
            t4.obroc(-7, -7, Math.toRadians(120));
            figury.add(t);
            figury.add(t2);
            figury.add(t3);
            figury.add(t4);
        } catch (Exception e) {
            System.err.println("Some error in visualization.");
            e.printStackTrace();
        }

    }

    public static void visualization() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.getContentPane().add(new GeometryTestVisualization());
                frame.setResizable(false);
                frame.setSize(width + 100, height + 100);
                frame.pack();

                frame.setLocationByPlatform(true);
                frame.setVisible(true);
            }
        });
    }

    public void addListeners() {

        addMouseWheelListener(new MouseWheelListener() {
            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                int notches = e.getWheelRotation();
                if (notches < 0) // up
                    zoomBase += 0.5;
                else //down
                    if (zoomBase > 0.5) zoomBase -= 0.5;

                zoom = Math.pow(zoomBase, zoomPow);
                repaint();
            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (DRAWING_POINT) {
                    Punkt p = new Punkt((e.getPoint().x - offset.x) / zoom, (-1) * (e.getPoint().y - offset.y) / zoom);
                    figury.add(p);
                } else if (DRAWING_LINE) {
                    if (DRAWING_LINE_POINT == 0) {
                        DRAWING_LINE_A = new Punkt((e.getPoint().x - offset.x) / zoom, (-1) * (e.getPoint().y - offset.y) / zoom);
                        DRAWING_LINE_POINT++;
                    } else if (DRAWING_LINE_POINT == 1) {
                        DRAWING_LINE_B = new Punkt((e.getPoint().x - offset.x) / zoom, (-1) * (e.getPoint().y - offset.y) / zoom);
                        DRAWING_LINE_POINT++;
                    }
                    if (DRAWING_LINE_POINT == 2) {
                        figury.add(new Odcinek(DRAWING_LINE_A, DRAWING_LINE_B));
                        DRAWING_LINE_POINT = 0;
                    }
                } else if (DRAWING_TRIANGLE) {
                    Punkt A = null, B = null, C = null;
                    if (DRAWING_TRIANGLE_POINT == 0) {
                        DRAWING_TRIANGLE_A = new Punkt((e.getPoint().x - offset.x) / zoom, (-1) * (e.getPoint().y - offset.y) / zoom);
                        DRAWING_TRIANGLE_POINT++;
                    } else if (DRAWING_TRIANGLE_POINT == 1) {
                        DRAWING_TRIANGLE_B = new Punkt((e.getPoint().x - offset.x) / zoom, (-1) * (e.getPoint().y - offset.y) / zoom);
                        DRAWING_TRIANGLE_POINT++;
                    } else if (DRAWING_TRIANGLE_POINT == 2) {
                        DRAWING_TRIANGLE_C = new Punkt((e.getPoint().x - offset.x) / zoom, (-1) * (e.getPoint().y - offset.y) / zoom);
                        DRAWING_TRIANGLE_POINT++;
                    }
                    if (DRAWING_TRIANGLE_POINT == 3) {
                        figury.add(new Trojkat(DRAWING_TRIANGLE_A, DRAWING_TRIANGLE_B, DRAWING_TRIANGLE_C));
                        DRAWING_TRIANGLE_POINT = 0;
                    }
                }
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                startingLocation = e.getLocationOnScreen();
                oldOffset.x = offset.x;
                oldOffset.y = offset.y;
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocusInWindow();
            }

        });

        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.isShiftDown()) {
                    offset.x = oldOffset.x + e.getLocationOnScreen().x - startingLocation.x;
                    offset.y = oldOffset.y + e.getLocationOnScreen().y - startingLocation.y;
                    repaint();
                }
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        DRAWING_POINT = true;
                        DRAWING_LINE = false;
                        DRAWING_TRIANGLE = false;
                        break;
                    case KeyEvent.VK_L:
                        DRAWING_POINT = false;
                        DRAWING_LINE = true;
                        DRAWING_TRIANGLE = false;
                        break;
                    case KeyEvent.VK_T:
                        DRAWING_POINT = false;
                        DRAWING_LINE = false;
                        DRAWING_TRIANGLE = true;
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_P:
                        DRAWING_POINT = false;
                        break;
                    case KeyEvent.VK_L:
                        DRAWING_LINE = false;
                        break;
                    case KeyEvent.VK_T:
                        DRAWING_TRIANGLE = false;
                        break;
                }
            }
        });

    }

    public void drawAxis(Graphics2D g) {
        g.setColor(AXIS_GUIDE_COLOR);
        g.setStroke(AXIS_GUIDE_STROKE);
        //vertical
        for (int i = GUIDE_DISTANCE; i <= width; i += GUIDE_DISTANCE) {
            g.drawLine(i * (int) zoom, -offset.y, i * (int) zoom, height - offset.y);
            g.drawLine(-i * (int) zoom, -offset.y, -i * (int) zoom, height - offset.y);
        }
        //horizontal
        for (int i = GUIDE_DISTANCE; i <= height; i += GUIDE_DISTANCE) {
            g.drawLine(-offset.x, i * (int) zoom, width - offset.x, i * (int) zoom);
            g.drawLine(-offset.x, -i * (int) zoom, width - offset.x, -i * (int) zoom);
        }

        g.setColor(AXIS_MAIN_COLOR);
        g.setStroke(AXIS_MAIN_STROKE);
        Line2D OX = new Line2D.Double(-offset.x, 0, width - offset.x, 0);
        Line2D OY = new Line2D.Double(0, -offset.y, 0, height - offset.y);
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
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        RenderingHints rh = new RenderingHints(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHints(rh);
        g.translate(offset.x, offset.y); // translate origin to offsetX , offsetY
        drawAxis(g2d);
        drawElements(g2d);
    }
}
