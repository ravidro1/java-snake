import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;

public class Button extends Rectangle {

    //    private int x;
//    private int y;
//    private int width;
//    private int height;
//
    private String text;
    //
    private Color color;
//
//    Rectangle rect;

    public Button(int x, int y, int width, int height, String text, Color color) {
//        this.x = x;
//        this.y = y;
//        this.width = width;
//        this.height = height;
        this.text = text;
        this.color = color;

        setSize(width, height);
        setLocation(x, y);

    }


    public void render(Graphics graphics) {

        graphics.setColor(color);
        graphics.fillRect(x, y, width, height);

        graphics.setColor(Color.white);
        graphics.setFont(new Font("Ariel", Font.BOLD, 25));

        DrawSting.draw(graphics, text, x + width / 2, y + height / 2);

        graphics.setColor(Color.red);
        graphics.drawLine(x, y + height / 2, x + width, y + height / 2);
        graphics.drawLine(x + width / 2, y, x + width / 2, y + height);
    }

    public boolean isClicked(MouseEvent e) {
//        if (e.getX() >= x && e.getX() <= x + width) {
//            if (e.getY() >= y && e.getY() <= y + height) {
//                System.out.println(e.getY() >= y);
//                System.out.println(x + " : " + e.getX());
//                System.out.println(y + " : " + e.getY());
//                return true;
//            }
//        }

        if (this.contains(e.getX(), e.getY())) {
            System.out.println(e.getX() + " : " + e.getY());
            return true;
        }
        return false;

    }
}
