package com.tictactoe.models;

/**
 *
 *
 */
public class Player {

    private String handlerId;
    private String name;
    private int totalScore;
    private String avatar;

    public Player(String handlerId, String name, int totalScore, String avatar) {
        this.handlerId = handlerId;
        this.name = name;
        this.totalScore = totalScore;
        this.avatar = avatar;
    }

    public String getHandlerId() {
        return handlerId;
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
