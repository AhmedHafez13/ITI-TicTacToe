package com.tictactoe.models;

/**
 *
 *
 */
public class Player {
    private String name;
    private int totalScore;
    private String avatar;

    public Player(String name, int totalScore, String avatar) {
        this.name = name;
        this.totalScore = totalScore;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getAvatar() {
        return avatar;
    }
}
