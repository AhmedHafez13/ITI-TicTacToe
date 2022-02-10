/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tictactoe.models;

/**
 *
 * @author AhmedHafez
 */
public class Player {
    private String name;
    private int totalScore;
    private boolean isOnline;
    private String avatar;

    public Player(String name, int totalScore, boolean isOnline, String avatar) {
        this.name = name;
        this.totalScore = totalScore;
        this.isOnline = isOnline;
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public String getAvatar() {
        return avatar;
    }
}
