package main;

public class Game implements Runnable {

    private GameWindow gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;

    public Game(){
        gamePanel = new GamePanel();
        gameWindow = new GameWindow(gamePanel);
        gamePanel.requestFocus();
        startGameLoop();
    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double timePerFrame = 1000000000.0 / FPS_SET;
        long lastFrame = System.nanoTime();
        long now ;
        int frames =0;
        long lastCkeck=System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();
            if (now - lastFrame >= timePerFrame) {
                gamePanel.repaint();
                lastFrame = now;
                frames++;

            }
            //Game Engine tip
            //Thread.yield(); // or Thread.sleep(1)
            if (System.currentTimeMillis()-lastCkeck>=1000){
                lastCkeck=System.currentTimeMillis();
                System.out.println("FPS:"+frames);
                frames=0;
            }
        }

    }
}
