package Main;

import java.awt.*;

public class Main {
    public static  void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = screenSize.getWidth();
        double height = screenSize.getHeight();
        Game game = new Game("Adventure", (int) width,(int) height);
        game.start();
    }
}