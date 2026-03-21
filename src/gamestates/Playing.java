package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import ui.PausedOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods{

    private Player player;
    private LevelManager levelManager;
    private PausedOverlay pausedOverlay;
    private boolean paused = false;


    public Playing(Game game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManager = new LevelManager(game);
        player = new Player(200,200, (int) (64 * Game.SCALE),(int) (40 * Game.SCALE));
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        pausedOverlay = new PausedOverlay(this);
    }

    @Override
    public void update() {
        if (!paused) {
            levelManager.update();
            player.update();
        } else
            pausedOverlay.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);

        if (paused)
            pausedOverlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getButton() == MouseEvent.BUTTON1) {
            player.setAttacking(true);
        }

    }

    public void mouseDragged(MouseEvent e) {
        if (paused)
            pausedOverlay.mouseDragged(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (paused)
            pausedOverlay.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {


        if (paused)
            pausedOverlay.mouseReleased(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {


        if (paused)
            pausedOverlay.mouseMoved(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A,KeyEvent.VK_4 -> player.setLeft(true);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D,KeyEvent.VK_6 -> player.setRight(true);
            case KeyEvent.VK_SPACE -> player.setJump(true);
            case KeyEvent.VK_BACK_SPACE -> Gamestate.state = Gamestate.MENU;
            case KeyEvent.VK_ESCAPE -> paused = !paused;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT, KeyEvent.VK_A,KeyEvent.VK_4 -> player.setLeft(false);
            case KeyEvent.VK_RIGHT, KeyEvent.VK_D,KeyEvent.VK_6 -> player.setRight(false);
            case KeyEvent.VK_SPACE -> player.setJump(false);
        }
    }

    public void unpauseGame() {
        paused = false;
    }

    public void windowFocusLost(){
        player.resetDirBooleans();
    }

    public Player getPlayer() {
        return player;
    }
}
