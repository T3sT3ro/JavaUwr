package W8.graphics;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Paint {
    public static Engine engine;

    public static void main(String[] args) {
        engine = Engine.getInstance();
        EventQueue.invokeLater(() -> {
            GUI gui = new GUI("Paint by Ttr", engine);
            engine.setImage(new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB));

            gui.pack();
            gui.setSize(1200, 800);
            gui.setLocationRelativeTo(null);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            gui.setVisible(true);

        });
    }
}
