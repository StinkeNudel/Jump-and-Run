package Main;

import java.awt.*;


public class Main {
    public static void main(String[] args) {
        double width = 1280;
        double height = 720;
        System.out.println("height" + height);
        System.out.println("width" + width);
        Launcher launcher = new Launcher("LAUNCHER", (int) width, (int) height);
        launcher.start();
    }
}