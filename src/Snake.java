import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    private int initLength = 5;

    private int x;
    private int y;

    private int direction;
    private int size = 20;

    public static final int speed = 20;

    private List<Node> nodeList = new ArrayList<Node>();

    public Snake() {
        this.x = 175 + (int) (Math.random() * (GamePanel.panelWidth - 350));
        this.y = 175 + (int) (Math.random() * (GamePanel.panelHeight - 350));
        this.direction = (int) Math.ceil(Math.random() * 4);
        System.out.println("sx: " + x);
        System.out.println("sy: " + y);
        initNodes();
    }

    private void initNodes() {
        int x = 0;
        int y = 0;
        for (int i = 1; i < initLength + 1; i++) {


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

            nodeList.add(new Node(x, y, size));
        }
    }


    public void changeDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case 38:
                if (direction == 1 || direction == 2 || nodeList.get(0).getX() == x)
                    return;
                direction = 1;
                break;
            case 40:
                if (direction == 1 || direction == 2 || nodeList.get(0).getX() == x)
                    return;
                direction = 2;

                break;
            case 37:
                if (direction == 3 || direction == 4 || nodeList.get(0).getY() == y)
                    return;
                direction = 3;

                break;
            case 39:
                if (direction == 3 || direction == 4 || nodeList.get(0).getY() == y)
                    return;
                direction = 4;

                break;
        }
    }


    public void render(Graphics graphics) {
        graphics.setColor(new Color(255, 0, 0));
        graphics.fillRect(x, y, size, size);

        for (int i = 0; i < nodeList.size(); i++) {
            nodeList.get(i).render(graphics);
        }
    }

    public void update() {
        updateLoc();
        checkSnakeCollision();
        checkBoundsCollision();
    }


    private void checkSnakeCollision() {
        for (int i = 2; i < nodeList.size(); i++) {
            Node currentNode = nodeList.get(i);
            if (collision(currentNode.getX(), currentNode.getY(), size, size))
                gameOver();
        }
    }

    private void checkBoundsCollision() {
        if (x < 0 || x > GamePanel.panelWidth || y < 0 || y > GamePanel.panelHeight) gameOver();
    }

    private void updateLoc() {
        int lastX = x;
        int lastY = y;
        if (direction == 1) y += -speed;
        else if (direction == 2) y += speed;
        else if (direction == 3) x += -speed;
        else if (direction == 4) x += speed;

        for (int i = 0; i < nodeList.size(); i++) {
            int tempLastX = nodeList.get(i).getX();
            int tempLastY = nodeList.get(i).getY();

            if (tempLastY > lastY) {
                nodeList.get(i).setDirection(1);
            }
            if (tempLastY < lastY) {
                nodeList.get(i).setDirection(2);
            }
            if (tempLastX > lastX) {
                nodeList.get(i).setDirection(3);
            }
            if (tempLastX < lastX) {
                nodeList.get(i).setDirection(4);
            }

            nodeList.get(i).setX(lastX);
            nodeList.get(i).setY(lastY);
            lastX = tempLastX;
            lastY = tempLastY;
        }
    }


    public boolean collision(int otherX, int otherY, int otherWidth, int otherHeight) {
        if ((x + size / 4 >= otherX && x + size / 4 <= otherX + otherWidth) || (x + size - size / 4 >= otherX && x + size - size / 4 <= otherX + otherWidth)) {
            if ((y + size / 4 >= otherY && y + size / 4 <= otherY + otherHeight) || (y + size - size / 4 >= otherY && y + size - size / 4 <= otherY + otherHeight)) {
                return true;
            }
        }
        return false;
    }

    public void addNode() {

        int newX = 0;
        int newY = 0;

        Node lastNode = nodeList.get(nodeList.size() - 1);
        System.out.println(lastNode.getDirection());
        switch (lastNode.getDirection()) {
            case 1:
                newX = lastNode.getX();
                newY = lastNode.getY() + size;
                break;
            case 2:
                newX = lastNode.getX();
                newY = lastNode.getY() - size;
                break;
            case 3:
                newX = lastNode.getX() + size;
                newY = lastNode.getY();
                break;
            case 4:
                newX = lastNode.getX() - size;
                newY = lastNode.getY();
                break;

        }

        nodeList.add(new Node(newX, newY, size));

    }


    private void gameOver() {
        System.out.println("Game Over!!!");
    }
}





