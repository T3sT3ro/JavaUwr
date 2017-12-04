package W8.graphics;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;

public class GUI extends JFrame {
    private Engine engine;

    private JList colorPalette;
    private JPanel rootPanel;
    private JPanel sidemenu;
    private JPanel canvas;
    private JLabel colorDescription;
    private JLabel footer;
    private JButton fileButton;
    private JButton zoomInButton;
    private JButton zoomOutButton;
    private JButton upButton;
    private JButton downButton;
    private JButton leftButton;
    private JButton rightButton;
    private JButton colorButton;
    private JToolBar toolbar;
    private JScrollPane canvasScroll;
    private JScrollPane colorPaletteScroll;
    private JSplitPane split;
    private JButton zoomResetButton;
    private JButton newImageButton;
    private JFileChooser fileChooser;
    private Border defaultBorder = BorderFactory.createLineBorder(ColorScheme.BORDER.get(), 1);
    private Border bottomBorder = BorderFactory.createMatteBorder(0, 0, 1, 0, ColorScheme.BORDER.get());
    private Font defaultFont = new Font(Font.SANS_SERIF, Font.PLAIN, 10);
    private DefaultListModel colorPaletteList;

    GUI(String title, Engine engine) {
        super(title);
        this.engine = engine;
        this.fileChooser = new JFileChooser();

        setContentPane(rootPanel);

        setTheme();
        addListeners();
        updateStaticElements();
        updateFooter();
    }

    private void createUIComponents() {
        // Button group


        // Color palette
        colorPaletteList = new DefaultListModel();
        colorPaletteList.addElement(new ColorItem("BLACK", Color.BLACK));
        colorPaletteList.addElement(new ColorItem("BLUE", Color.BLUE));
        colorPaletteList.addElement(new ColorItem("CYAN", Color.CYAN));
        colorPaletteList.addElement(new ColorItem("GRAY", Color.GRAY));
        colorPaletteList.addElement(new ColorItem("DARK GRAY", Color.DARK_GRAY));
        colorPaletteList.addElement(new ColorItem("GREEN", Color.GREEN));
        colorPaletteList.addElement(new ColorItem("LIGHT GRAY", Color.LIGHT_GRAY));
        colorPaletteList.addElement(new ColorItem("MAGENTA", Color.MAGENTA));
        colorPaletteList.addElement(new ColorItem("ORANGE", Color.ORANGE));
        colorPaletteList.addElement(new ColorItem("PINK", Color.PINK));
        colorPaletteList.addElement(new ColorItem("RED", Color.RED));
        colorPaletteList.addElement(new ColorItem("WHITE", Color.WHITE));
        colorPaletteList.addElement(new ColorItem("YELLOW", Color.YELLOW));
        colorPaletteList.addElement(new ColorItem("CUSTOM RED", ColorScheme.PALETTE_RED.get()));
        colorPaletteList.addElement(new ColorItem("CUSTOM BLUE", ColorScheme.PALETTE_BLUE.get()));
        colorPaletteList.addElement(new ColorItem("CUSTOM GREEN", ColorScheme.PALETTE_GREEN.get()));
        colorPaletteList.addElement(new ColorItem("CUSTOM YELLOW", ColorScheme.PALETTE_YELLOW.get()));
        colorPalette = new JList(colorPaletteList);

        // canvas
        canvas = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                super.paintComponent(g2);
                BufferedImage img = engine.getImage();
                if (img != null) {
                    g2.drawImage(img, engine.getTransformation(), this);
                    updateCanvasViewport();
                }

            }
        };
    }

    private void setTheme() {
        rootPanel.setBackground(ColorScheme.ROOT_BACKGROUND.get());

        toolbar.setBorder(bottomBorder);
        toolbar.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        newImageButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        fileButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        zoomInButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        zoomOutButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        zoomResetButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        upButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        downButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        leftButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        rightButton.setBackground(ColorScheme.ROOT_BACKGROUND.get());

        sidemenu.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        split.setBorder(defaultBorder);
        split.setBackground(ColorScheme.ROOT_BACKGROUND.get());

        colorPaletteScroll.setBorder(bottomBorder);
        colorPalette.setBackground(ColorScheme.ROOT_BACKGROUND.get());
        colorPalette.setForeground(ColorScheme.TEXT.get());
        colorPalette.setSelectionBackground(ColorScheme.MENU_SELECTED.get());
        colorPalette.setSelectionForeground(ColorScheme.TEXT.get());
        colorButton.setBackground(ColorScheme.PALETTE_BLUE.get());
        colorButton.setBorder(defaultBorder);
        colorDescription.setForeground(ColorScheme.TEXT.get());
        colorDescription.setFont(defaultFont);

        canvasScroll.setBorder(null);
        canvas.setBackground(ColorScheme.BACKGROUND.get());

        footer.setForeground(ColorScheme.TEXT.get());
        footer.setFont(defaultFont);
    }

    private void addListeners() {
        // new imageListener
        newImageButton.addActionListener(e -> {
            engine.setImage(new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB));
            updateCanvasViewport();
            updateStaticElements();
            updateFooter();
            repaint();
        });

        // fileOpenListener
        fileButton.addActionListener(e -> {
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                engine.setFile(fileChooser.getSelectedFile());
                updateCanvasViewport();
                updateStaticElements();
                updateFooter();
                repaint();
            }
        });

        // zoomIn listener
        zoomInButton.addActionListener(e -> {
            JScrollBar vertical = canvasScroll.getVerticalScrollBar();
            JScrollBar horizontal = canvasScroll.getHorizontalScrollBar();
            // divide by old scale
            vertical.setValue((int) (vertical.getValue() / engine.getTransformation().getScaleX()));
            horizontal.setValue((int) (horizontal.getValue() / engine.getTransformation().getScaleY()));
            // update scale
            engine.zoomIn();
            // multiply by new scale
            vertical.setValue((int) (vertical.getValue() * engine.getTransformation().getScaleX()));
            horizontal.setValue((int) (horizontal.getValue() * engine.getTransformation().getScaleY()));
            canvas.repaint();
            updateCanvasViewport();
            updateFooter();
        });

        // zoomOut listener
        zoomOutButton.addActionListener(e -> {
            JScrollBar vertical = canvasScroll.getVerticalScrollBar();
            JScrollBar horizontal = canvasScroll.getHorizontalScrollBar();
            // divide by old scale
            vertical.setValue((int) (vertical.getValue() / engine.getTransformation().getScaleX()));
            horizontal.setValue((int) (horizontal.getValue() / engine.getTransformation().getScaleY()));
            // update scale
            engine.zoomOut();
            // multiply by new scale
            vertical.setValue((int) (vertical.getValue() * engine.getTransformation().getScaleX()));
            horizontal.setValue((int) (horizontal.getValue() * engine.getTransformation().getScaleY()));
            canvas.repaint();
            updateCanvasViewport();
            updateFooter();
        });

        zoomResetButton.addActionListener(e -> {
            engine.resetZoom();
            updateCanvasViewport();
            updateFooter();
        });

        upButton.addActionListener(e -> {
            JScrollBar vertical = canvasScroll.getVerticalScrollBar();
            vertical.setValue(vertical.getMinimum());
        });

        downButton.addActionListener(e -> {
            JScrollBar vertical = canvasScroll.getVerticalScrollBar();
            vertical.setValue(vertical.getMaximum());
        });

        leftButton.addActionListener(e -> {
            JScrollBar horizontal = canvasScroll.getHorizontalScrollBar();
            horizontal.setValue(horizontal.getMinimum());
        });

        rightButton.addActionListener(e -> {
            JScrollBar horizontal = canvasScroll.getHorizontalScrollBar();
            horizontal.setValue(horizontal.getMaximum());
        });

        // JList color palette listener
        colorPalette.addListSelectionListener(e -> {
            if (colorPalette.getSelectedValue() != null) {
                engine.setColor(((ColorItem) colorPalette.getSelectedValue()).color);
                engine.setMode(Engine.Mode.PENCIL);
            }
            updateStaticElements();
        });

        // colorButton listener
        colorButton.addActionListener(e -> {
            Color selected = JColorChooser.showDialog(null, "Choose a color", engine.getColor());
            if (selected != null) {
                engine.setColor(selected);
                colorPalette.clearSelection();
                engine.setMode(Engine.Mode.PENCIL);
            }
            updateStaticElements();
        });

        // canvas mouse info listener
        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                if (e.getSource() == canvas) {
                    Point mouse = engine.toImageCoordinates(e.getPoint());
                    if (mouse.x < engine.getImage().getWidth() && mouse.y < engine.getImage().getHeight()) {
                        engine.setLastMousePos(engine.toImageCoordinates(e.getPoint()));
                        updateFooter();
                    }
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                mouseMoved(e);
            }
        });

        // canvas drawing
        canvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (engine.getImage() == null || e.getSource() != canvas)
                    return;
                if (engine.getMode() == Engine.Mode.PENCIL)
                    engine.draw(e.getPoint());
                canvas.repaint();
            }
        });


        canvas.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (engine.getImage() == null || e.getSource() != canvas)
                    return;
                if (engine.getMode() == Engine.Mode.PENCIL)
                    engine.draw(e.getPoint());
                canvas.repaint();
            }

        });
    }

    private void updateStaticElements() {
        colorButton.setBackground(engine.getColor());
        colorDescription.setText(String.format(
                "#%02x%02x%02x",
                engine.getColor().getRed(),
                engine.getColor().getGreen(),
                engine.getColor().getBlue()));
    }

    private void updateCanvasViewport() {
        BufferedImage img = engine.getImage();
        if (img != null)
            canvas.setPreferredSize(
                    new Dimension(
                            (int) (img.getWidth() * engine.getTransformation().getScaleX()),
                            (int) (img.getHeight() * engine.getTransformation().getScaleY())
                    )
            );
        canvas.revalidate();
        canvasScroll.updateUI();
    }

    private void updateFooter() {
        footer.setText(
                "x: " + engine.getLastMousePos().x +
                        ", y: " + engine.getLastMousePos().y +
                        ", zoom: x" + engine.getTransformation().getScaleX());
    }

    enum ColorScheme {
        ROOT_BACKGROUND(Color.decode("#3c3f41")),
        MENU_SELECTED(Color.decode("#0d293e")),
        BORDER(Color.decode("#555555")),
        TEXT(Color.decode("#adadad")),
        BACKGROUND(Color.decode("#2b2b2b")),
        PALETTE_RED(Color.decode("#ff6b6b")),
        PALETTE_GREEN(Color.decode("#c8f463")),
        PALETTE_BLUE(Color.decode("#4ecdc4")),
        PALETTE_YELLOW(Color.decode("#fff153"));

        Color c;

        ColorScheme(Color c) {
            this.c = c;
        }

        public Color get() {
            return this.c;
        }

    }

    private class ColorItem {
        String name;
        Color color;

        public ColorItem(String name, Color color) {
            this.name = name;
            this.color = color;
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
