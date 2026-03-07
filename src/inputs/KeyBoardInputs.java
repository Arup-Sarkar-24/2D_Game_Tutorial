package inputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utilz.Contants.Directions.*;

public class KeyBoardInputs implements KeyListener {

    private GamePanel gamePanel;

    public KeyBoardInputs(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W,KeyEvent.VK_8 -> gamePanel.setDirection(UP);
            case KeyEvent.VK_DOWN, KeyEvent.VK_X,KeyEvent.VK_2 -> gamePanel.setDirection(DOWN);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A,KeyEvent.VK_4 -> gamePanel.setDirection(LEFT);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D,KeyEvent.VK_6 -> gamePanel.setDirection(RIGHT);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP, KeyEvent.VK_W,KeyEvent.VK_8 :
            case KeyEvent.VK_DOWN, KeyEvent.VK_X,KeyEvent.VK_2 :
            case KeyEvent.VK_LEFT, KeyEvent.VK_A,KeyEvent.VK_4 :
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D,KeyEvent.VK_6 :
                gamePanel.setMoving(false);
                break;
        }
    }
}
