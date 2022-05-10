package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Ground {

    private BufferedImage image;
    private Rectangle position;
     static final int height = 2;

    public Ground(int type, int x, int y){
        switch (type){
            case 1: {
                loadImage("ground_up.png");
                break;
            }
            case 2: {
                loadImage("ground_sub.png");
                break;
            }
        }
        position = new Rectangle(x, y, Board.TILE_SIZE, Board.TILE_SIZE);
    }

    private void loadImage(String filename){
        try{
            image = ImageIO.read(new File("src/images/" + filename));
        }catch(IOException exception){
            System.out.println("Error loading image file: " + exception.getMessage());
        }
    }

    public void draw(Graphics g, ImageObserver observer) {
        g.drawImage(
                image,
                position.x * Board.TILE_SIZE,
                position.y * Board.TILE_SIZE,
                observer
        );
    }

}
