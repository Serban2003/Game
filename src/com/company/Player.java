package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Player {
    private BufferedImage image;
    private Rectangle position;
    private int score;
    private int step = 50;
    private int width = 100;
    private int height = 100;

    public Player(){
        loadImage();
        position = new Rectangle(0, 11, width, height);
        score = 0;
    }

    private void loadImage(){
        try{
            image = ImageIO.read(new File("src/images/player.png"));
        }catch(IOException exception){
            System.out.println("Error loading image file: " + exception.getMessage());
        }
    }
    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                position.x * step,
                position.y * step,
                observer
        );
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        System.out.print(key + ": ");

        if(key == KeyEvent.VK_UP){
            position.translate(0, -1);
            System.out.println("UP");
        }
        if(key == KeyEvent.VK_RIGHT){
            position.translate(1, 0);
            System.out.println("RIGHT");
        }
        if(key == KeyEvent.VK_DOWN){
            position.translate(0, 1);
            System.out.println("DOWN");
        }
        if(key == KeyEvent.VK_LEFT){
            position.translate(-1, 0);
            System.out.println("LEFT");
        }

        System.out.println(getPosition().toString());
    }

    public void tick(){
        if(position.x < 0) position.x = 0;
        else if(position.x + 2 >= Board.COLS ) position.x = Board.COLS - 2;

        if(position.y < 0) position.y = 0;
        else if(position.y + 2 >= Board.ROWS - Ground.height + 1) position.y = Board.ROWS -  Ground.height  - 2;
    }

    public String getScore(){
        return String.valueOf(score);
    }

    public void addScore(int amount){
        score += amount;
    }

    public Rectangle getPosition(){
        return position;
    }
}
