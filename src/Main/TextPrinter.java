package Main;

import java.awt.*;

public class TextPrinter {
    private static int textCounter;
    private static int textDelay;
    private static String output = "";
    private int x, y;

    public  TextPrinter(){
    }

    public static void addText(String text, int x, int y, Graphics g){
        String input = text;
        if (textDelay == 0) {
            if (textCounter < text.length()) {
                char letter = input.charAt(textCounter);
                output += letter;
                textCounter++;
            }
        }
        textDelay++;
        if (textDelay >= 7) {
            textDelay = 0;
        }

        g.setFont(new Font("Monospaced", Font.BOLD, 25));
        g.setColor(Color.black);
        g.drawString(output, x, y);
    }

    public static void clearText(){
        output = "";
        textCounter = 0;
    }
}