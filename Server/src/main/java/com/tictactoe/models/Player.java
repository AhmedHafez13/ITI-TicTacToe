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
