package com.company;

import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class Player {
    private BufferedImage image;
    private javafx.scene.shape.Rectangle rectangle;
    private int score;
    private int step = 50;
    private int width = 100;
    private int height = 100;

    public Player(){
        loadImage();
        rectangle = new javafx.scene.shape.Rectangle(0, 11, width, height);
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
                rectangle.xProperty().intValue() * step,
                rectangle.yProperty().intValue() * step,
                observer
        );
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        System.out.print(key + ": ");

        if(key == KeyEvent.VK_UP){
            rectangle.setY(rectangle.getY() - 3);
            TimerTask task = new TimerTask() {
                public void run() {
                    rectangle.setY(rectangle.getY() + 3);
                }
            };
            Timer timer = new Timer("Timer");

            long delay = 500L;
            timer.schedule(task, delay);
            System.out.println("UP");
        }
        if(key == KeyEvent.VK_RIGHT){
            rectangle.setX(rectangle.getX() + 1);
            System.out.println("RIGHT");
        }
        if(key == KeyEvent.VK_DOWN){
            rectangle.setY(rectangle.getY() + 1);
            System.out.println("DOWN");
        }
        if(key == KeyEvent.VK_LEFT){
            rectangle.setX(rectangle.getX() - 1);
            System.out.println("LEFT");
        }
        System.out.println(getRectangle().toString());
    }

    public void tick(){
        if(rectangle.getX() < 0) rectangle.setX(0);
        else if(rectangle.getX() + 2 >= Board.COLS ) rectangle.setX(Board.COLS - 2);

        if(rectangle.getY() < 0) rectangle.setY(0);
        else if(rectangle.getY() + 2 >= Board.ROWS - Ground.height + 1) rectangle.setY(Board.ROWS -  Ground.height  - 2);
    }

    public String getScore(){
        return String.valueOf(score);
    }

    public void addScore(int amount){
        score += amount;
    }

    public Rectangle getRectangle(){
        return rectangle;
    }
}
