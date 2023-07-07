import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    public KeyHandler() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Game.snake.changeDirection(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
