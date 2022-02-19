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

    private final PlayerHandler playerX;
    private final PlayerHandler playerO;
    private final String gameId;
    private ArrayList<Integer> moves = new ArrayList<>();
    private boolean isGameOver = false;
    private String winnerId;
    private char started = PLAYER_X;

    public Game(PlayerHandler playerX, PlayerHandler playerO, String gameId) {
        this.playerX = playerX;
        this.playerO = playerO;
        this.gameId = gameId;
    }

    public ArrayList getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Integer> moves) {
        this.moves = moves;
    }

    public String getMovesJsonStr() {
        String movesJsonStr = null;
        // TODO: convert moves to json string to insert moves into the database
        return movesJsonStr;
    }

    public PlayerHandler getPlayerX() {
        return playerX;
    }

    public PlayerHandler getPlayerO() {
        return playerO;
    }

    public String getGameId() {
        return gameId;
    }

    public ArrayList<Integer> setNextMove(String index) {
        moves.add(Integer.parseInt(index));
        /*
         * TODO: Check if the game is over (isGameOver)
         * set (winnerId) when the game is ended
         */
        return moves;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    public String getWinnerId() {
        return winnerId;
    }
}
