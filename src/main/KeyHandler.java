package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    private boolean[] keyStates = new boolean[256];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyStates[keyCode] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        keyStates[keyCode] = false;
    }

    public boolean isKeyActive(int keyCode) {
        if (keyCode == KeyEvent.VK_W) {
            return keyStates[KeyEvent.VK_W];
        } else if (keyCode == KeyEvent.VK_S) {
            return keyStates[KeyEvent.VK_S];
        } else if (keyCode == KeyEvent.VK_A) {
            return keyStates[KeyEvent.VK_A];
        } else if (keyCode == KeyEvent.VK_D) {
            return keyStates[KeyEvent.VK_D];
        } else if (keyCode == KeyEvent.VK_SPACE){
            return keyStates[KeyEvent.VK_SPACE];
        }
        else if(keyCode == KeyEvent.VK_1)
        {
            return keyStates[KeyEvent.VK_1];
        }
        else if(keyCode == KeyEvent.VK_2)
        {
            return keyStates[KeyEvent.VK_2];
        }
        else if(keyCode == KeyEvent.VK_3)
        {
            return keyStates[KeyEvent.VK_3];
        }
        else if(keyCode == KeyEvent.VK_4)
        {
            return keyStates[KeyEvent.VK_4];
        }
        else if(keyCode == KeyEvent.VK_5)
        {
            return keyStates[KeyEvent.VK_5];
        }
        else if(keyCode == KeyEvent.VK_ESCAPE)
        {
            return keyStates[KeyEvent.VK_ESCAPE];
        }
        else if(keyCode == KeyEvent.VK_P)
        {
            return keyStates[KeyEvent.VK_P];
        }
        else {
            return false;
        }
    }
}
