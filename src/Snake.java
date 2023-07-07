import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private int defaultLength = 30;


    private int x = 100;
    private int y = 100;
    private int direction = 1;
    private int size = 20;

    public static final int speed = 20;

    private List<Node> nodeList = new ArrayList<Node>();

    public Snake() {
        initNodes();
    }

    private void initNodes() {
        int x = 0;
        int y = 0;
        for (int i = 1; i < defaultLength + 1; i++) {


            switch (direction) {
                case 1:
                    x = this.x;
                    y = this.y + (i * size);
                    break;
                case 2:
                    x = this.x;
                    y = this.y - (i * size);
                    break;
                case 3:
                    x = this.x + (i * size);
                    y = this.y;
                    break;
                case 4:
                    x = this.x - (i * size);
                    y = this.y;
                    break;

            }

            nodeList.add(new Node(x, y, speed, size, direction));
        }
    }


    public void changeDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
                if (direction == 1 || direction == 2) return;
                changeNodesDirection(1);
                direction = 1;
                break;
            case 40:
                if (direction == 1 || direction == 2) return;
                if (direction != 1) {
                    changeNodesDirection(2);
                    direction = 2;
                }
                break;
            case 37:
                if (direction == 3 || direction == 4) return;
                if (direction != 4) {
                    changeNodesDirection(3);
                    direction = 3;
                }
                break;
            case 39:
                if (direction == 3 || direction == 4) return;
                if (direction != 3) {
                    changeNodesDirection(4);
                    direction = 4;
                }
                break;
        }
    }

    private void changeNodesDirection(int newDirection) {
//        for (int i = 0; i < nodeList.size(); i++) {
//            nodeList.get(i).addTurn(newDirection, x, y);
//        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.fillRect(x, y, size, size);

        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).render(graphics);
        }
    }

    public void update() {
        int lastX = x;
        int lastY = y;
        if (direction == 1) y += -speed;
        else if (direction == 2) y += speed;
        else if (direction == 3) x += -speed;
        else if (direction == 4) x += speed;

        for (int i = 0; i < nodeList.size(); i++) {
            int tempLastX = nodeList.get(i).getX();
            int tempLastY = nodeList.get(i).getY();
            nodeList.get(i).setX(lastX);
            nodeList.get(i).setY(lastY);
            lastX = tempLastX;
            lastY = tempLastY;
        }

    }

//    private void addNode() {
//
//    }


}
