package Main;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width;
        double height = screenSize.height;
        System.out.println("height" + height);
        System.out.println("width" + width);
        Game game = new Game("Adventure", (int) width, (int) height);
        game.start();
    }
}