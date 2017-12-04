package W8.graphics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Engine {

    private static final double SCALE_MAX = 8;
    private static Engine ourInstance = new Engine();
    private Mode mode;
    private Color color;
    private File file;
    private BufferedImage image;
    private double zoom = 1.0;
    private int brushSize = 1;
    private Point lastMousePos = new Point(0, 0);
    private AffineTransform transformation = new AffineTransform();

    private Engine() {
        color = Color.BLACK;
        mode = Mode.PENCIL;
    }

    public static Engine getInstance() {
        return ourInstance;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
        try {
            setImage(ImageIO.read(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void zoomIn() {
        if (zoom < SCALE_MAX) {
            transformation.scale(1f / zoom, 1f / zoom);
            if (zoom >= 1.0)
                zoom += 1.0;
            else
                zoom = 1.0 / (1.0 / zoom - 1.0);
            transformation.scale(zoom, zoom);
        }
    }

    public void zoomOut() {
        if (zoom > 1.0 / SCALE_MAX) {
            transformation.scale(1f / zoom, 1f / zoom);
            if (zoom > 1)
                zoom -= 1.0;
            else
                zoom = 1.0 / (1.0 / zoom + 1.0);
            transformation.scale(zoom, zoom);
        }

    }

    public Point toImageCoordinates(Point p) {
        return new Point((int) (p.x / transformation.getScaleX()), (int) (p.y / transformation.getScaleX()));
    }

    public AffineTransform getTransformation() {
        return this.transformation;
    }

    public void resetZoom() {
        zoom = 1;
        transformation.setToIdentity();
    }

    public Point getLastMousePos() {
        return lastMousePos;
    }

    public void setLastMousePos(Point lastMousePos) {
        this.lastMousePos = lastMousePos;
    }

    public void draw(Point pos) {
        Point p = toImageCoordinates(pos);
        if (p.x < 0 || p.x >= image.getWidth() || p.y < 0 || p.y >= image.getHeight())
            return;
        image.setRGB(p.x, p.y, color.getRGB());
    }

    ///////////
    public enum Mode {
        PENCIL, PICKER

    }
}
