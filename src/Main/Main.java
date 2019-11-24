package Main;

import java.awt.*;

public class Main {
    public static  void main(String[] args){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double width = 1920;
        double height = 1080;
        Game game = new Game("Adventure", (int) width,(int) height);
        game.start();
    }
}