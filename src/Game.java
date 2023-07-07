import java.awt.*;

public class Game {

    public Snake snake;
    private Apple currentApple;


    public Game() {
        snake = new Snake();
        System.out.println(GamePanel.panelWidth);

        int appleX = 50 + (int) (Math.random() * (GamePanel.panelWidth - 100));
        int appleY = 50 + (int) (Math.random() * (GamePanel.panelHeight - 100));
        currentApple = new Apple(appleX, appleY);

        System.out.println("ax: " + appleX);
        System.out.println("ay: " + appleY);
    }

    public void render(Graphics graphics) {

        currentApple.render(graphics);
        snake.render(graphics);
    }

    public void update() {
        snake.update();
        checkAppleEat();
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
