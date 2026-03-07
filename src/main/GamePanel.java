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
    private BufferedImage img, subImg;


    public GamePanel(){
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyBoardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
        setPanelSize();
        importImg();

    }

    private void importImg() {
        InputStream is = getClass().getResourceAsStream("/player_sprites.png");
        try {
            img = ImageIO.read(is);
        }catch (Exception e){
            e.printStackTrace();
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        subImg= img.getSubimage(1*64,8*40,64,40);

        g.drawImage(subImg,(int)xDelta,(int)yDelta,128,80,null);
    }
}
