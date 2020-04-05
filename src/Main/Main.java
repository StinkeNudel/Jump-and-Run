package Main;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.width;
        double height = screenSize.height;
        System.out.println("height" + height);
        System.out.println("width" + width);
        Game game = new Game("Wir wissen noch nicht wie es heißen soll. Was aber wirklich interessant ist, ist, wie viel Text hier oben reinpasst                                                                   HI                                                       ah hier ist Ende", (int) width, (int) height);
        game.start();
    }
}