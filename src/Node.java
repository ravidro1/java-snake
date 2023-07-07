import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Node {
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

    private List<Turn> turnList = new ArrayList<Turn>();

    private int x;
    private int y;

    private int currentDirection;
    private int speed;
    private int size;

    public Node(int x, int y, int speed, int size, int currentDirection) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.size = size;
        this.currentDirection = currentDirection;
    }

    public void addTurn(int direction, int x, int y) {
        // direction:
        // up = 1
        // down = 2
        // left = 3
        // right = 4

        System.out.println(currentDirection);
        turnList.add(new Turn(direction, x, y));
    }

    public void update() {
        if (currentDirection == 1) y += -speed;
        else if (currentDirection == 2) y += speed;
        else if (currentDirection == 3) x += -speed;
        else if (currentDirection == 4) x += speed;

        if (turnList.size() > 0) {
            Turn nextTurn = turnList.get(0);

            switch (nextTurn.getDirection()) {
                case 1:
                case 2:
//                    System.out.println(1);
//                    if (currentDirection != 3 && currentDirection != 4) return;

                    if ((currentDirection == 3 && x <= nextTurn.getX()) || (currentDirection == 4 && x >= nextTurn.getX())) {
                        currentDirection = nextTurn.getDirection();
                        x = nextTurn.getX();
                        turnList.remove(0);
                    }
                    break;

                case 3:
                case 4:
//                    System.out.println(2);

//                    if (currentDirection != 1 && currentDirection != 2) return;

                    if ((currentDirection == 1 && y <= nextTurn.getY()) || (currentDirection == 2 && y >= nextTurn.getY())) {
                        currentDirection = nextTurn.getDirection();
                        y = nextTurn.getY();
                        turnList.remove(0);
                    }
                    break;


            }
        }
    }

    public void render(Graphics graphics) {
        graphics.setColor(new Color((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255)));
//        graphics.setColor(Color.red);
        graphics.fillRect(x, y, size, size);
    }


    class Turn {

        private int direction;
        private int x;
        private int y;

        public int getDirection() {
            return direction;
        }

        public void setDirection(int direction) {
            this.direction = direction;
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

        public Turn(int direction, int x, int y) {
            this.direction = direction;
            this.x = x;
            this.y = y;
        }
    }
}
