import java.awt.*;

public class Game {

    public static Snake snake = new Snake();


    public static void render(Graphics graphics) {

        snake.render(graphics);
    }

    public static void update() {
        snake.update();

    }
}
