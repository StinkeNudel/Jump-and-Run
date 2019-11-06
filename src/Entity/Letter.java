package Entity;

import GFX.ImageLoader;
import Main.ArrayLists;
import Main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Letter extends Entity {
    private BufferedImage image = ImageLoader.loadImage("/Scroll_Open-12.png.png");

    private Game game;
    int scrollImage;

    //long open = System.currentTimeMillis();
    //int howToFly = 0;


    public Letter(Game game, double x, double y) {
        super(x, y);
        this.game = game;
    }

    public void tick() {
        move();
    }

    public void render(Graphics g) {
        g.drawImage(image, (int) x, (int) y, 104, 138, null);
    }

    public void move() {

        if (x >= game.width / 2) {
            x = x - 4;
        } else {
            switch (scrollImage) {
                case 0:
                    image = ImageLoader.loadImage("/Scroll_Open-11.png.png");
                    scrollImage++;
                    break;
                case 1:
                    image = ImageLoader.loadImage("/Scroll_Open-10.png.png");
                    scrollImage++;
                    break;
                case 2:
                    image = ImageLoader.loadImage("/Scroll_Open-9.png.png");
                    scrollImage++;
                    break;
                case 3:
                    image = ImageLoader.loadImage("/Scroll_Open-8.png.png");
                    scrollImage++;
                    break;
                case 4:
                    image = ImageLoader.loadImage("/Scroll_Open-7.png.png");
                    scrollImage++;
                    break;
                case 5:
                    image = ImageLoader.loadImage("/Scroll_Open-6.png.png");
                    scrollImage++;
                    break;
                case 6:
                    image = ImageLoader.loadImage("/Scroll_Open-5.png.png");
                    scrollImage++;
                    break;
                case 7:
                    image = ImageLoader.loadImage("/Scroll_Open-4.png.png");
                    scrollImage++;
                    break;
                case 8:
                    image = ImageLoader.loadImage("/Scroll_Open-3.png.png");
                    scrollImage++;
                    break;
                case 9:
                    image = ImageLoader.loadImage("/Scroll_Open-2.png.png");
                    scrollImage++;
                    break;
                case 10:
                    image = ImageLoader.loadImage("/Scroll_Open-1.png.png");
                    scrollImage++;
                    break;
            }
        }

        /**
         * if (open <= 5) {
         x = x - 4;
         open = System.currentTimeMillis();
         }
         */

        /**
         *
         ArrayList player = ArrayLists.player;
         Player f = (Player)player.get(0);

         if (Math.abs(this.getX() - f.getX()) > Math.abs(this.getY() - f.getY())) {
         if (f.getX() > this.getX()) {
         x = x + 4;
         } else {
         x = x - 4;
         }
         } else {
         if (f.getY() > this.getY()) {
         y = y - 4;
         } else {
         y = y + 4;
         }

         }
         */

        /**
         *
         switch (howToFly) {
         case 0: //Scroll fliegt von rechts rein, geradeaus
         x = x - 4;
         if (x == game.width - 500) {
         howToFly++;
         }
         break;
         case 1: //Scroll fliegt nach schräg oben links
         x = x - 2;
         y = y - 2;
         if (x <= game.width - 550 && y <= game.height - 600) {
         howToFly++;
         }
         break;
         case 2: //Scroll fliegt kurz nur nach oben
         y = y - 4;
         if (x <= game.width - 550 && y <= game.height - 630) {
         howToFly++;
         }
         break;
         case 3: //Scroll fliegt nach rechts oben
         x = x + 2;
         y = y - 2;
         if (x >= game.width - 550 && y <= game.height - 650) {
         howToFly++;
         }
         break;
         case 4: //Scroll fliegt kurz nur nach rechts
         x = x + 4;
         if (x >= game.width - 520 && y <= game.height - 650) {
         howToFly++;
         }
         break;
         case 5: //Scroll fliegt nach rechts unten
         x = x + 2;
         y = y + 2;
         if (x >= game.width - 500 && y >= game.height - 600) {
         howToFly++;
         }
         break;
         case 6: //Scroll kurz nur nach unten
         y = y + 4;
         if (x >= game.width - 500 && y >= game.height - 580) {
         howToFly++;
         }
         break;
         case 7: //Scroll fliegt nach links unten
         x = x - 2;
         y = y + 2;
         if (x <= game.width - 550 && y >= game.height - 500) {
         howToFly++;
         }
         break;
         */
    }

}

