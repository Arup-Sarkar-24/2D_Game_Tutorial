package main;

import javax.swing.*;

public class GameWindow {
    private JFrame jFrame;

    public GameWindow(){
        jFrame = new JFrame();
        jFrame.setSize(800,600);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jFrame.setVisible(true);
    }
}
