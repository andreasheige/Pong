package com.ah;

public class Score extends HighscoreList{
    private int score;

    public Score() {
        score = 0;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void increaseScore(int value) {
        score += value;
    }
}
