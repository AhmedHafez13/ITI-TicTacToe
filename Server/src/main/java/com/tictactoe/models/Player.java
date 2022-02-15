package com.tictactoe.models;

/**
 *
 *
 */
public class Player {
    private String name;
    private int totalScore;
    private String avatar;
    private int id;


    public Player(int id,String name, int totalScore, String avatar) {
        this.name = name;
        this.totalScore = totalScore;
        this.avatar = avatar;
        this.id = id;
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
