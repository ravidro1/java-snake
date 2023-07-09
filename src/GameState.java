import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class GameState extends State {

    public Snake snake;
    private Apple currentApple;

    private boolean isInPauseMode = false;
    private boolean isGameStart = false;
    private boolean isInGameOver = false;

    private final int UNIT_SIZE = 20;

    public GameState() {
        snake = new Snake(this, UNIT_SIZE);

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
            DrawSting.draw(graphics, "Press Space Key For Go To Game Over Screen", GamePanel.panelWidth / 2, GamePanel.panelHeight / 2 + 150);
        }

        graphics.setFont(new Font("Ariel", 2, 15));
        graphics.drawString("FPS: " + String.valueOf(GamePanel.totalFps), 25, 25);
        graphics.drawString("Snake Length: " + String.valueOf(snake.snakeLength), 25, 45);

//        drawDevGrid(graphics);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }


//    private void drawDevGrid(Graphics graphics) {
//        graphics.setColor(Color.green);
//        for (int i = 0; i <= GamePanel.panelWidth / UNIT_SIZE; i++) {
//            graphics.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, GamePanel.panelHeight);
//        }
//        for (int i = 0; i <= GamePanel.panelHeight / UNIT_SIZE; i++) {
//            graphics.drawLine(0, i * UNIT_SIZE, GamePanel.panelWidth, i * UNIT_SIZE);
//        }
//    }


    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE && isInGameOver) {
            GamePanel.state = new GameOverState(snake.snakeLength);
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameStart) isGameStart = true;
        if (!isGameStart || isInGameOver) return;

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE && isGameStart) setIsInPauseMode();
        if (isInPauseMode) return;
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
