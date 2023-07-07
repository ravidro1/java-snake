import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;


public class GamePanel extends JPanel {
    private static final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600;

    public static KeyHandler keyHandler = new KeyHandler();

    public static int panelWidth;
    public static int panelHeight;

    public GamePanel() {
        new Window(this, SCREEN_WIDTH, SCREEN_HEIGHT);
        this.panelWidth = getWidth();
        this.panelHeight = getHeight();
    }

    public void gameLoop() {

        double currentTime;
        double lastSecondTime = System.nanoTime() / 1000000000.0;
        double lastFrameTime = System.nanoTime() / 1000000000.0;
        double lastUpdateTime = System.nanoTime() / 1000000000.0;

        int timePassInSecond = 0;

        final double FPS = 60.0;
        final double UPS = 25.0;

        final double timeToRender = 1 / FPS;
        final double timeToUpdate = 1 / UPS;

        int fpsCount = 0;
        int upsCount = 0;


        while (true) {
            currentTime = System.nanoTime() / 1000000000.0;

            if (currentTime - lastFrameTime >= timeToRender) {
                repaint();
                fpsCount++;
                lastFrameTime = System.nanoTime() / 1000000000.0;
            }
            if (currentTime - lastUpdateTime >= timeToUpdate) {
                update();
                upsCount++;
                lastUpdateTime = System.nanoTime() / 1000000000.0;
            }

            if (currentTime - lastSecondTime >= 1) {
//                System.out.println("Time Pass: " + formatTime(timePassInSecond));
//                System.out.println("FPS: " + fpsCount);
//                System.out.println("UPS: " + upsCount);
//                System.out.println("\n");

                fpsCount = 0;
                upsCount = 0;
                lastSecondTime = System.nanoTime() / 1000000000.0;
                timePassInSecond++;

                this.panelWidth = getWidth();
                this.panelHeight = getHeight();
            }
        }
    }

    private String formatTime(int timeInSeconds) {
        int hours = timeInSeconds / 3600;
        int moduloHours = timeInSeconds % 3600;

        int minutes = moduloHours / 60;
        int seconds = moduloHours % 60;


        String secondInString = String.valueOf(seconds);
        String minutesInString = String.valueOf(minutes);
        String hoursInString = String.valueOf(hours);

        if (secondInString.length() == 1) secondInString = "0" + secondInString;
        if (minutesInString.length() == 1) minutesInString = "0" + minutesInString;
        if (hoursInString.length() == 1) hoursInString = "0" + hoursInString;

        return hoursInString + ":" + minutesInString + ":" + secondInString;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, getWidth(), getHeight());
//        drawDevGrid(graphics);

        Game.render(graphics);


    }

    private void drawDevGrid(Graphics graphics) {
        final int rectSize = 5;
        graphics.setColor(Color.green);
        for (int i = 0; i <= panelWidth / rectSize; i++) {
            graphics.drawLine(i * rectSize, 0, i * rectSize, panelHeight);
        }
        for (int i = 0; i <= panelHeight / rectSize; i++) {
            graphics.drawLine(0, i * rectSize, panelWidth, i * rectSize);
        }
    }


    private void update() {
        Game.update();
    }


}
