package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private float xDelta=100, yDelta=100;
    private BufferedImage img;
    private BufferedImage[] idleAni;
    private int aniTick, aniIndex, aniSpeed=15;


    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
        importImg();
        loadAnimations();

    }

    private void loadAnimations() {
        idleAni = new BufferedImage[5];

        for (int i = 0; i < idleAni.length; i++)
            idleAni[i] = img.getSubimage(i*64, 0,64,40);
    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                is.close();
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void setPanelSize() {
        Dimension size = new Dimension(640, 400);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    public void changeXDelta(int value){
        this.xDelta += value;
    }

    public void changeYDelta(int value){
        this.yDelta += value;
    }

    public void setPos (int x,  int y){
        this.xDelta = x;
        this.yDelta = y;
    }
    private void updateAnimationTick(){
        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= idleAni.length){
                aniIndex = 0;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //add a sub image
        //g.drawImage(subImg,(int)xDelta,(int)yDelta,128,80,null);

        updateAnimationTick();

        //add animation
        g.drawImage(idleAni[aniIndex],(int) xDelta,(int) yDelta,(int)(64*1.5),(int)(40*1.5),null);
    }
}
