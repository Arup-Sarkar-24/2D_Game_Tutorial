package main;

import inputs.KeyBoardInputs;
import inputs.MouseInputs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import static utilz.Contants.PlayerConstants.*;
import static utilz.Contants.Directions.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private int xDelta=256, yDelta=256;
    private BufferedImage img;
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed=15;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false;


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
        animations = new BufferedImage[9][6];

        for (int i = 0; i < animations.length; i++)
            for (int j = 0; j < animations[i].length; j++)
                animations[i][j] = img.getSubimage(j*64, i*40,64,40);
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
        //setMinimumSize(size);
        //setMaximumSize(size);
    }

    public void setDirection( int direction){
        this.playerDir = direction;
        moving = true;
    }

    public void setMoving( boolean moving){
        this.moving = moving;
    }

    private void updateAnimationTick(){

        aniTick++;
        if (aniTick >= aniSpeed){
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= GetSpriteAmount(playerAction)){
                aniIndex = 0;
            }
        }


    }

    private void setAnimation(){
        if (moving)
            playerAction = RUNNING;
        else
            playerAction = IDLE;
    }

    private void updatePos(){
        if (moving){
            switch (playerDir){
                case LEFT -> xDelta -= 5;
                case RIGHT -> xDelta += 5;
                case UP -> yDelta -= 5;
                case DOWN -> yDelta += 5;
            }
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        //add a sub image
        //g.drawImage(subImg,(int)xDelta,(int)yDelta,128,80,null);

        updateAnimationTick();

        setAnimation();
        updatePos();

        //add animation
        g.drawImage(animations[playerAction][aniIndex],xDelta,yDelta,(int)(64*1.5),(int)(40*1.5),null);
    }
}
