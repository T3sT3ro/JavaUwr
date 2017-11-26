package W7.grafika;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.Deque;
import java.util.LinkedList;

class PaintWindow extends Frame {
    private final static Color palette[] = new Color[]{
            Color.decode("#cc241d"),
            Color.decode("#98971a"),
            Color.decode("#fabd2f"),
            Color.decode("#458588"),
            Color.decode("#b16286"),
            Color.decode("#689d6a")
    };
    private final static Color bgColorDark = Color.decode("#2b2b2b");
    private final static Color bgColorLight = Color.decode("#3c3f41");
    private final static Color textColor = Color.decode("#bbbbbb");
    private final static Color strokeColorActive = Color.decode("#595b5d");
    private static final int width = 1200;
    private static final int height = 800;
    private final Canvas canvas = new Canvas();
    private final Deque<Kreska> strokes = new LinkedList<>(); // contains strokes
    private Panel menu;
    private Graphics2D offscreenGraphics; // graphics for buffer image offscreen
    private BufferedImage offscreen; // double buffering
    private boolean DRAWING_FLAG = false;
    private Point activeBegin = new Point();
    private Point activeEnd = new Point();
    private Color selectedColor = palette[0];

    public PaintWindow() {
        super("Paint");
        setSize(width, height);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Build side menu
        buildMenu();

        // Build footer
        Label footer = new Label("Draw by dragging mouse on canvas, RMB on canvas for focus, Backspace: clear canvas, F: erase first stroke, B or L: erase latest stroke");
        footer.setForeground(textColor);
        footer.setBackground(bgColorLight);

        // compose window
        add(menu, BorderLayout.LINE_START);
        add(footer, BorderLayout.SOUTH);
        add(canvas, BorderLayout.CENTER);

        // offscreen image buffer to render elements

        addListeners();
        setVisible(true);
        repaint();
    }

    private void buildMenu() {
        menu = new Panel(new BorderLayout());
        menu.setBackground(bgColorLight);

        Panel colorPicker = new Panel(new GridLayout(1 + palette.length, 1, 3, 3));

        Label colorPickerLabel = new Label("Color picker", Label.CENTER);
        colorPickerLabel.setForeground(textColor);
        colorPicker.add(colorPickerLabel);

        CheckboxGroup colorCheckboxGroup = new CheckboxGroup();

        Checkbox colorOption[] = new Checkbox[palette.length];

        for (int i = 0; i < colorOption.length; i++) {
            if (i == 0)
                colorOption[i] = new Checkbox("", colorCheckboxGroup, true);
            else
                colorOption[i] = new Checkbox("", colorCheckboxGroup, false);
            colorOption[i].setBackground(palette[i]);
            ColorPaletteListener cl = new ColorPaletteListener(palette[i]);
            colorOption[i].addItemListener(cl);
            colorPicker.add(colorOption[i]);
        }


        menu.add(colorPicker, BorderLayout.NORTH);
    }

    private void addListeners() {
        // request focus for key listeners
        requestFocusInWindow();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                PaintWindow.this.dispose();
            }
        });

        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getComponent() == canvas && e.getButton() == MouseEvent.BUTTON1) {
                    activeBegin = e.getPoint();
                    DRAWING_FLAG = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    strokes.addLast(new Kreska(activeBegin, e.getPoint(), selectedColor));
                    DRAWING_FLAG = false;
                    repaint();
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                requestFocusInWindow();
            }
        });

        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (e.getComponent() == canvas) {
                    activeEnd = e.getPoint();
                    repaint();
                }
            }
        });

        canvas.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_BACK_SPACE:
                        strokes.clear();
                        break;
                    case KeyEvent.VK_L:
                    case KeyEvent.VK_B:
                        if (!strokes.isEmpty())
                            strokes.removeLast();
                        break;
                    case KeyEvent.VK_F:
                        if (!strokes.isEmpty())
                            strokes.removeFirst();
                        break;
                }
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g) {

        // Double buffering
        if (offscreen == null)
            offscreen = new BufferedImage(canvas.getWidth(), canvas.getHeight(), BufferedImage.TYPE_INT_RGB);
        if (offscreenGraphics == null)
            offscreenGraphics = (Graphics2D) offscreen.getGraphics();

        // clear bg
        offscreenGraphics.setColor(bgColorDark);
        offscreenGraphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // enable AA and set properties
        offscreenGraphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        offscreenGraphics.setStroke(new BasicStroke(2f));

        // Write strokes to buffered image
        for (Kreska stroke : strokes) {
            if (stroke.poczatek == null || stroke.koniec == null || stroke.kolor == null)
                System.err.println("===== NULL =====");
            else {
                offscreenGraphics.setColor(stroke.kolor);
                offscreenGraphics.drawLine(stroke.poczatek.x, stroke.poczatek.y, stroke.koniec.x, stroke.koniec.y);
            }
        }
        if (DRAWING_FLAG) {
            offscreenGraphics.setColor(strokeColorActive);
            offscreenGraphics.drawLine(activeBegin.x, activeBegin.y, activeEnd.x, activeEnd.y);
        }

        canvas.getGraphics().drawImage(offscreen, 0, 0, this);
    }

    class ColorPaletteListener implements ItemListener {
        final Color color;

        ColorPaletteListener(Color color) {
            this.color = color;
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            selectedColor = color;
        }
    }


}

public class PaintApp extends Frame {
    public static void main(String[] args) {
        new PaintWindow();
    }
}
