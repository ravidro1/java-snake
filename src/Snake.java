import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    GameState game;

    private int initLength = 5;
    public int snakeLength = 0;


    private int x;
    private int y;

    private int direction;
    private int unitSize;


    private List<Node> nodeList = new ArrayList<Node>();

    public Snake(GameState game, int unitSize) {
        this.x = 175 + (int) (Math.random() * (GamePanel.panelWidth - 350));
        this.y = 175 + (int) (Math.random() * (GamePanel.panelHeight - 350));
        this.direction = (int) Math.ceil(Math.random() * 4);


        this.game = game;
        this.unitSize = unitSize;

        initNodes();
    }


    private void initNodes() {
        int x = 0;
        int y = 0;
        for (int i = 1; i < initLength + 1; i++) {


            switch (direction) {
                case 1:
                    x = this.x;
                    y = this.y + (i * unitSize);
                    break;
                case 2:
                    x = this.x;
                    y = this.y - (i * unitSize);
                    break;
                case 3:
                    x = this.x + (i * unitSize);
                    y = this.y;
                    break;
                case 4:
                    x = this.x - (i * unitSize);
                    y = this.y;
                    break;

            }

            nodeList.add(new Node(x, y, unitSize));
            snakeLength++;
        }
    }


    public void changeDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                if (direction == 1 || direction == 2 || nodeList.get(0).getX() == x)
                    return;
                direction = 1;
                break;
            case KeyEvent.VK_DOWN:
                if (direction == 1 || direction == 2 || nodeList.get(0).getX() == x)
                    return;
                direction = 2;

                break;
            case KeyEvent.VK_LEFT:
                if (direction == 3 || direction == 4 || nodeList.get(0).getY() == y)
                    return;
                direction = 3;

                break;
            case KeyEvent.VK_RIGHT:
                if (direction == 3 || direction == 4 || nodeList.get(0).getY() == y)
                    return;
                direction = 4;

                break;
        }
    }


    public void render(Graphics graphics) {
        graphics.setColor(new Color(255, 0, 0));
        graphics.fillRect(x, y, unitSize, unitSize);

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
            if (collision(currentNode.getX(), currentNode.getY(), unitSize, unitSize))
                gameOver();
        }
    }

    private void checkBoundsCollision() {
        if (x < 0 || x > GamePanel.panelWidth || y < 0 || y > GamePanel.panelHeight) gameOver();
    }

    private void updateLoc() {
        int lastX = x;
        int lastY = y;
        if (direction == 1) y += -unitSize;
        else if (direction == 2) y += unitSize;
        else if (direction == 3) x += -unitSize;
        else if (direction == 4) x += unitSize;

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
        if ((x + 1 >= otherX && x + 1 <= otherX + otherWidth) || (x + unitSize - 1 >= otherX && x + unitSize - 1 <= otherX + otherWidth)) {
            if ((y + 1 >= otherY && y + 1 <= otherY + otherHeight) || (y + unitSize - 1 >= otherY && y + unitSize - 1 <= otherY + otherHeight)) {
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
                newY = lastNode.getY() + unitSize;
                break;
            case 2:
                newX = lastNode.getX();
                newY = lastNode.getY() - unitSize;
                break;
            case 3:
                newX = lastNode.getX() + unitSize;
                newY = lastNode.getY();
                break;
            case 4:
                newX = lastNode.getX() - unitSize;
                newY = lastNode.getY();
                break;

        }

        nodeList.add(new Node(newX, newY, unitSize));
        snakeLength++;


    }


    private void gameOver() {
        System.out.println("Game Over!!!");
        game.setInGameOver(true);
    }
}





