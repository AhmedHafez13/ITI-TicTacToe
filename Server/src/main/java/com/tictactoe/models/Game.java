/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tictactoe.models;

import java.util.ArrayList;

public class Game {

    final char PLAYER_X = 'X';
    final char PLAYER_O = 'O';

    private int playerXId;
    private int playerOId;
    private ArrayList<Integer> moves = new ArrayList<>();
    char started = PLAYER_X;
    char winner;

    public Game(int playerXId, int playerOId) {
        this.playerXId = playerXId;
        this.playerOId = playerOId;
    }

    public ArrayList getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Integer> moves) {
        this.moves = moves;
    }

    public void setMoves(String jsonStr) {
        // TODO: parse the jsonStr and set moves data to the moves ArrayList
    }

    public String getMovesJsonStr() {
        String movesJsonStr = null;
        // TODO: convert moves to json string to insert moves into the database
        return movesJsonStr;
    }

    public int getPlayerXId() {
        return playerXId;
    }

    public int getPlayerOId() {
        return playerOId;
    }

    public void setNextMove() {
        // TODO: add the next move the moves ArrayList
    }
}
