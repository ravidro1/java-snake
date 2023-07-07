import java.awt.*;


public class Node {

    public static int length = 0;

    private int x;
    private int y;

    private int size;

    public int direction;

    public Node(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        length++;
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

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void update() {
    }

    public void render(Graphics graphics) {
//        graphics.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
        graphics.setColor(new Color(150, 0, 0));
        graphics.fillRect(x, y, size, size);
    }


}
