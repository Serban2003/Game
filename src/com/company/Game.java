package com.company;

import javax.swing.*;

public class Game {

    private static void initializeWindow(){
        JFrame window = new JFrame("A Game?");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Board board = new Board();
        window.add(board);
        window.addKeyListener(board);

        window.setResizable(false);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                initializeWindow();
            }
        });
    }
}
