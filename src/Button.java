import Input.ButtonHandler;

import javax.swing.*;
import java.awt.*;

public class Button extends JFrame {
    private JButton start;

    public Button(){
        super("hi");
        setLayout(new FlowLayout());

        start = new JButton("start");
        add(start);

        ButtonHandler handler = new ButtonHandler();
    }
}
