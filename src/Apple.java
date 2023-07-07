import java.awt.*;

public class Apple {

    private int x;
    private int y;
    private int size = 25;

    public Apple(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void render(Graphics graphics) {
        graphics.setColor(new Color(87, 28, 4));
        graphics.fillOval(x, y, size, size);
    }


}
