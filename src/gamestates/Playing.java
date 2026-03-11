package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{

    private Player player;
    private LevelManager levelManager;


    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200,200, (int) (64 * Game.SCALE),(int) (40 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            //case KeyEvent.VK_UP, KeyEvent.VK_W,KeyEvent.VK_8 -> player.setUp(true);
            //case KeyEvent.VK_DOWN, KeyEvent.VK_X,KeyEvent.VK_2 -> player.setDown(true);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A,KeyEvent.VK_4 -> player.setLeft(true);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D,KeyEvent.VK_6 -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
            case KeyEvent.VK_BACK_SPACE -> Gamestate.state = Gamestate.MENU;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            //case KeyEvent.VK_UP, KeyEvent.VK_W,KeyEvent.VK_8 -> player.setUp(false);
            //case KeyEvent.VK_DOWN, KeyEvent.VK_X,KeyEvent.VK_2 -> player.setDown(false);
            case KeyEvent.VK_LEFT, KeyEvent.VK_A,KeyEvent.VK_4 -> player.setLeft(false);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D,KeyEvent.VK_6 -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
        }
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }
}
