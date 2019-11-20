package com.ah;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;


public class Game extends JPanel {

    Ball ball;
    Racket racket;
    Font scoreFont;
    private HighscoreList highscoreList;

    private int timeLeft;
    private Game game;

    public Game(Game game) {
        this.game = game;
        setPreferredSize(new Dimension(Main.width, Main.height));

        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent e) { // Not in use
            }

            public void keyPressed(KeyEvent e) {
                racket.keyPrssed(e);
            }

            public void keyReleased(KeyEvent e) {
                racket.keyReleased(e);
            }
        });
        setFocusable(true);
        init();
    }

    private void init() {
        Score score = new Score();
        highscoreList = new HighscoreList();
        ball = new Ball(this, score);
        racket = new Racket(this);
        scoreFont = new Font("Helvetica", Font.PLAIN, 12);
    }

    // startar spel

    void startGame() {

        ISound.playSound("3_2_1.wav"); // Countdown wav
        countDown();
        while (true) {
            while (true) {
                if (move()) {
                    break;
                }
                repaint();
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            init();
        }
    }


    private boolean move() {
        if (ball.moveBall()) {
            return true;
        }
        racket.move();
        return false;
    }

    // Målar upp/ut på panel

    public void paint(Graphics g) { // paint up the panel/frame for game
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ball.paint(g2d);
        racket.paint(g2d);
        g2d.setFont(scoreFont);
        g2d.drawString("Score: " + ball.getScore(), 10, 30);
        if (timeLeft > 0)
            g2d.drawString("Ball drops in: " + timeLeft, Main.width / 2, Main.height / 2);

    }

    // Game Over

    public boolean gameOver() {
        ISound.playSound("game_over.wav");
        highscoreList.updateHighscore("Player", ball.getScore());
        String[] buttons = {"Exit game", "Play again"};
        int dialogButtonResult = JOptionPane.showOptionDialog(null, "PLAY AGAIN?", "Game Over", JOptionPane.WARNING_MESSAGE, 0, null, buttons, buttons[0]);

        if (dialogButtonResult == 0) {
            System.exit(0);
        }
        return true; // YES PLZ button
    }

    public void countDown() { // Countdown 3 s. berfor ball drops
        try {
            for (timeLeft = 3; timeLeft > 0; timeLeft--) {
                repaint();
                TimeUnit.SECONDS.sleep(1);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
