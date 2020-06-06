package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    private boolean[] keys;
    public boolean w, a, s, d, up, enter, space, p, o, b, e;

    public KeyHandler() {
        keys = new boolean[256];
    }

    public void tick(){
        w = keys[KeyEvent.VK_W];
        a = keys[KeyEvent.VK_A];
        s = keys[KeyEvent.VK_S];
        d = keys[KeyEvent.VK_D];
        p = keys[KeyEvent.VK_P];
        o = keys[KeyEvent.VK_O];
        b = keys[KeyEvent.VK_B];
        up = keys[KeyEvent.VK_UP];
        enter = keys[KeyEvent.VK_ENTER];
        space = keys[KeyEvent.VK_SPACE];
        e = keys[KeyEvent.VK_E];
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
