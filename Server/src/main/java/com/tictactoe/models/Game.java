package com.tictactoe.models;

import com.tictactoe.server.PlayerHandler;
import java.util.ArrayList;

/**
 *
 *
 */
public class Game {

    final char PLAYER_X = 'X';
    final char PLAYER_O = 'O';

    private PlayerHandler playerXId;
    private PlayerHandler playerOId;
    private ArrayList<Integer> moves = new ArrayList<>();
    char started = PLAYER_X;
    char winner;

    public Game(PlayerHandler playerXId, PlayerHandler playerOId) {
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

    public PlayerHandler getPlayerXId() {
        return playerXId;
    }

    public PlayerHandler getPlayerOId() {
        return playerOId;
    }

    public void setNextMove() {
        // TODO: add the next move the moves ArrayList
    }
}
