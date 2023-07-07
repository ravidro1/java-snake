import javax.swing.*;
import java.awt.image.BufferStrategy;

public class Window {
    private JFrame frame;

    private static final String TITLE = "Snake";

    public Window(GamePanel gamePanel, int screenWidth, int screenHeight) {
        ImageIcon img = new ImageIcon("src/assets/snake_icon.png");

        frame = new JFrame();
        frame.setContentPane(gamePanel);
        frame.setSize(screenWidth, screenHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setTitle(TITLE);
        frame.addKeyListener(GamePanel.keyHandler);
        frame.setIconImage(img.getImage());
        frame.setIgnoreRepaint(true);
//        frame.pack();

        frame.setVisible(true);

    }
}
