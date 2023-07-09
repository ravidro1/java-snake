import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class State {

    public abstract void update();


    public abstract void render(Graphics graphics);


    public abstract void mousePressed(MouseEvent e);

    public abstract void keyPressed(KeyEvent e);


}
