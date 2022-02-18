package com.tictactoe.models;

/**
 *
 *
 */
public class Player {

    private int id;
    private String name;
    private int totalScore;
    private String avatar;
    private boolean isOnline = false;

    public Player(int id, String name, int totalScore, String avatar, boolean isOnline) {
        this.id = id;
        this.name = name;
        this.totalScore = totalScore;
        this.avatar = avatar;
        this.isOnline = isOnline;
    }

    public int getId() {
        return id;
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

    public boolean isOnline() {
        return isOnline;
    }
}
