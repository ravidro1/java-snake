import java.awt.*;
import java.awt.event.MouseEvent;

public class GameOverState extends State {


    private int snakeLength;

    private Button homeButton;
    private Button playAgainButton;
    private Button exitButton;

    public GameOverState(int snakeLength) {
        this.snakeLength = snakeLength;

        homeButton = new Button(GamePanel.panelWidth / 2 - 125, (int) (GamePanel.panelHeight * 0.45), 250, 50, "Go To Home Screen", Color.DARK_GRAY);
        playAgainButton = new Button(GamePanel.panelWidth / 2 - 125, (int) (GamePanel.panelHeight * 0.6), 250, 50, "Play Again", Color.DARK_GRAY);
        exitButton = new Button(GamePanel.panelWidth / 2 - 125, (int) (GamePanel.panelHeight * 0.75), 250, 50, "Exit", Color.DARK_GRAY);
    }

    public void update() {

    }

    public void render(Graphics graphics) {
        graphics.setColor(new Color(0, 28, 48));
        graphics.fillRect(0, 0, GamePanel.panelWidth, GamePanel.panelHeight);

        graphics.setFont(new Font("Ariel", Font.BOLD, 50));
        DrawSting.draw(graphics, "Game Over", GamePanel.panelWidth / 2, 100);


        graphics.setFont(new Font("Ariel", Font.BOLD, 30));
        DrawSting.draw(graphics, "Snake Length: " + snakeLength, GamePanel.panelWidth / 2, 200);

        homeButton.render(graphics);
        playAgainButton.render(graphics);
        exitButton.render(graphics);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);

        if (homeButton.isClicked(e)) {
            GamePanel.state = new HomeState();
        }
        if (playAgainButton.isClicked(e)) {
            GamePanel.state = new GameState();
        }
        if (exitButton.isClicked(e)) {
            System.exit(0);

        }
    }
}
