package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Board extends JPanel implements ActionListener, KeyListener {
    private final int tick = 25;
    public static final int TILE_SIZE = 50;
    public static final int ROWS = 15;
    public static final int COLS = 20;

    private Timer timer;
    private Player player;

    public Board(){
        setPreferredSize(new Dimension(TILE_SIZE * COLS, TILE_SIZE * ROWS));
        setBackground(new Color(16, 149, 201, 255));

        player = new Player();

        timer = new Timer(tick, this);
        timer.start();
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        drawBackground(graphics);
        drawGround(graphics);
        player.draw(graphics, this);

        // this smooths out animations on some systems
        Toolkit.getDefaultToolkit().sync();
    }

    @Override
    public  void actionPerformed(ActionEvent e){
        player.tick();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        player.keyPressed(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e){
    }

    private void drawBackground(Graphics graphics){
        graphics.setColor(new Color(37, 151, 250, 232));
        for(int row = 0; row < ROWS; ++row)
            for(int col = 0 ; col < COLS; ++col){
                if((row + col) % 2 == 1)
                    graphics.fillRect(col * TILE_SIZE,
                                        row* TILE_SIZE,
                                        TILE_SIZE,
                                        TILE_SIZE
                        );
            }
    }

    private void drawGround(Graphics graphics){
        int row = ROWS;

        while(row > ROWS - Ground.height){
            for(int col = 0; col < COLS; ++col){
                Ground ground = new Ground(2, col, row);
                ground.draw(graphics, this);
            }
            row--;
        }
        for(int col = 0; col < COLS; ++col){
            Ground ground = new Ground(1, col, ROWS - Ground.height);
            ground.draw(graphics, this);
        }
    }
}
