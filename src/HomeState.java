import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;


public class HomeState extends State {

    private Button playButton;
    private Button exitButton;

    public HomeState() {
        playButton = new Button(GamePanel.panelWidth / 2 - 100, GamePanel.panelHeight / 5 * 3, 200, 50, "Play", Color.DARK_GRAY);
        exitButton = new Button(GamePanel.panelWidth / 2 - 100, GamePanel.panelHeight / 5 * 3 + 100, 200, 50, "Exit", Color.DARK_GRAY);
    }

    public void update() {
    }


    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 28, 48));
        graphics.fillRect(0, 0, GamePanel.panelWidth, GamePanel.panelHeight);

        graphics.setFont(new Font("Ariel", Font.BOLD, 50));
        DrawSting.draw(graphics, "Snake", GamePanel.panelWidth / 2, 150);

        playButton.render(graphics);
        exitButton.render(graphics);

    }


    public void mousePressed(MouseEvent e) {
        if (playButton.isClicked(e)) {
            GamePanel.state = new GameState();
        }
        if (exitButton.isClicked(e)) {

            System.exit(0);
        }


    }


}
