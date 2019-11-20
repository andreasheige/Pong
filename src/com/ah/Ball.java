package com.ah;

import java.awt.*;
import java.util.Random;

public class Ball {

    Random rand = new Random();

    // Boll info

    private static final int D = 7;
    private int x = -1;
    private int y = -1;
    private int moveX = 2;
    private int moveY = 2;
    private Score score;
    Color ballColor = new Color(0xff1e00);
    private Game game;


    public  Ball(Game game, Score score) {
        this.score = score;
        this.game = game;
    }

    // Random start på X & Y axel
    private void randomBallStart(){
        randomBallStartX();
        randomBallStartY();
    }
    private void randomBallStartX() {

        if (x == -1) {
            int minX = 25;
            int maxX = game.getWidth() - 25;
            while (x < minX) {
                x = rand.nextInt(maxX);
            }

            System.out.println("x:" + x); // Skriver ut rand posX
        }
    }

    private void randomBallStartY(){

        if(y == -1){
            int minY = 25;
            int maxY = game.getHeight()/4;
            while(y < minY){
                y = rand.nextInt(maxY);
            }

            System.out.println("y:" + y); // Skriver ut rand posY
        }

    }


    boolean moveBall() {
        randomBallStart();          //   Boll start och vart den
                                    // ska ta vägen vid kollision

        if (x + moveX < 0)
            moveX = 2;
        if (x + moveX > game.getWidth() - D)
            moveX = -2;
        if (y + moveY < 0)
            moveY = 2;
        if (y + moveY > game.getHeight() - D)
            if(game.gameOver()) {
                return true;
            }
        if(collision()){
            moveY = - 2;
            y = game.racket.getY() - D;
            setScore(1);
            ISound.playSound("racket.wav");
        }
        x += moveX;
        y += moveY;
        return false;
    }

    public void paint(Graphics2D g) {
        g.setColor(ballColor);
        g.fillRect(x, y, D, D);
    }

    Rectangle getBounds(){
        return new Rectangle(x, y,D,D );
    }
    boolean collision(){
        return game.racket.getBounds().intersects(getBounds());
    }

    public int getScore() {
        return score.getScore();
    }


    public void setScore(int score) {
        this.score.increaseScore(score);
    }
}