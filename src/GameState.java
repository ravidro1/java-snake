import java.awt.*;
import java.awt.event.KeyEvent;

public class GameState extends State {

    public Snake snake;
    private Apple currentApple;

    private boolean isInPauseMode = false;
    private boolean isGameStart = false;
    private boolean isInGameOver = false;


    public GameState() {
        snake = new Snake(this);

        int appleX = 50 + (int) (Math.random() * (GamePanel.panelWidth - 100));
        int appleY = 50 + (int) (Math.random() * (GamePanel.panelHeight - 100));
        currentApple = new Apple(appleX, appleY);

    }


    public void setInGameOver(boolean inGameOver) {
        isInGameOver = inGameOver;
    }

    public void render(Graphics graphics) {
        graphics.setColor(new Color(1, 69, 12));
        graphics.fillRect(0, 0, GamePanel.panelWidth, GamePanel.panelHeight);
        currentApple.render(graphics);
        snake.render(graphics);

        graphics.setColor(Color.white);

        if (isInPauseMode) {
            graphics.setFont(new Font("Ariel", 1, 40));
            DrawSting.draw(graphics, "Pause", GamePanel.panelWidth / 2, GamePanel.panelHeight / 2);

        }
        if (!isGameStart) {
            graphics.setFont(new Font("Ariel", 1, 40));
            DrawSting.draw(graphics, "For Start Press On Space Key", GamePanel.panelWidth / 2, GamePanel.panelHeight / 2);
        }
        if (isInGameOver) {
            graphics.setFont(new Font("Ariel", 1, 40));
            DrawSting.draw(graphics, "GameOver", GamePanel.panelWidth / 2, GamePanel.panelHeight / 2);
            graphics.setFont(new Font("Ariel", 1, 20));
            DrawSting.draw(graphics, "Press Any Key For Go To Game Over", GamePanel.panelWidth / 2, GamePanel.panelHeight / 2 + 150);
        }

        graphics.setFont(new Font("Ariel", 2, 15));
        graphics.drawString("FPS: " + String.valueOf(GamePanel.totalFps), 25, 25);
        graphics.drawString("Snake Length: " + String.valueOf(snake.snakeLength), 25, 45);
    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (isInGameOver) {
            GamePanel.state = new GameOverState(snake.snakeLength);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameStart) isGameStart = true;
        if (!isGameStart || isInGameOver) return;

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && isGameStart) setIsInPauseMode();
        if (isInPauseMode) return;
        super.keyPressed(e);
        snake.changeDirection(e);
    }

    public void update() {
        if (isInPauseMode || !isGameStart || isInGameOver) return;
        snake.update();
        checkAppleEat();
    }

    public void setIsInPauseMode() {
        isInPauseMode = !isInPauseMode;
    }

    private void checkAppleEat() {
        if (snake.collision(currentApple.getX(), currentApple.getY(), currentApple.getSize(), currentApple.getSize())) {
            snake.addNode();
            int appleX = 50 + (int) (Math.random() * (GamePanel.panelWidth - 100));
            int appleY = 50 + (int) (Math.random() * (GamePanel.panelHeight - 100));
            currentApple = new Apple(appleX, appleY);
        }
    }
}
