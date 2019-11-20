package com.ah;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Racket {

    // Storlek, placering, färg mm på racket

    private static final int WIDTH = 70;
    private static final int HEIGHT = 5;
    private int x = 200;
    private static final int Y = 685;
    private int moveX = 0;
    Color racketColor = new Color(0x000000);
    private Game game;

    public Racket(Game game) {
        this.game = game;
    }

    public void paint(Graphics2D g) {
        g.setColor(racketColor);
        g.fillRect(x,Y,WIDTH, HEIGHT);
    }

    void move() {
        if (x + moveX > 0 && x + moveX < game.getWidth() - 70)
            x += moveX;
    }

    public void keyPrssed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            moveX = -3;
        if (e.getKeyCode()== KeyEvent.VK_RIGHT)
            moveX = 3;
    }

    public void keyReleased(KeyEvent e) {
        moveX = 0;

    }
    public Rectangle getBounds(){
        return new Rectangle(x,Y,WIDTH, HEIGHT);
    }
    public int getY(){
        return Y;
    }
}

