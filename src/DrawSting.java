import java.awt.*;

public class DrawSting {

    public static void draw(Graphics graphics, String text, int x, int y) {
        int stringWidth = (int) graphics.getFontMetrics().getStringBounds(text, graphics).getWidth();
        int stringHeight = (int) graphics.getFontMetrics().getStringBounds(text, graphics).getHeight();
        graphics.setColor(Color.white);
        graphics.drawString(text, x - stringWidth / 2, y + stringHeight / 2);
    }


}
