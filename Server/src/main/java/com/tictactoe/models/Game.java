package com.tictactoe.models;

import com.tictactoe.server.PlayerHandler;
import java.util.ArrayList;

/**
 *
 *
 */
public class Game {

    public final static char PLAYER_X = 'X';
    public final static char PLAYER_O = 'O';
    public final static char DRAW = 'D';
    public final static char RUNNING = 'R';

    private final PlayerHandler playerX;
    private final PlayerHandler playerO;
    private final String gameId;
    private ArrayList<Integer> moves = new ArrayList<>();
    private boolean isGameOver = false;
    private char winnerPlayer = RUNNING;
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

    public boolean isGameOver() {
        return isGameOver;
    }

    public PlayerHandler getWinnerPlayerHandler() {
        return winnerPlayer == PLAYER_X ? playerX : playerO;
    }

    public PlayerHandler getLoserPlayerHandler() {
        return winnerPlayer == PLAYER_O ? playerX : playerO;
    }

    public ArrayList<Integer> setNextMove(String index) {
        moves.add(Integer.parseInt(index));
        return moves;
    }

    private static ArrayList<String> winningMoves = new ArrayList<String>() {
        {
            add("012");
            add("345");
            add("678");
            add("036");
            add("147");
            add("258");
            add("048");
            add("246");
        }
    };

    public char getGameResult() {
        if (moves.size() >= 5 && moves.size() % 2 != 0) {
            // X played the last and may win
            if (isInWinningMoves(getPlayerMoves(PLAYER_X))) {
                winnerPlayer = PLAYER_X;
                return PLAYER_X;
            }
        } else if (moves.size() >= 6 && moves.size() % 2 == 0) {
            // O played the last and may win
            if (isInWinningMoves(getPlayerMoves(PLAYER_O))) {
                winnerPlayer = PLAYER_O;
                return PLAYER_O;
            }
        }
        if (moves.size() == 9) {
            // End of the game with draw
            winnerPlayer = DRAW;
            return DRAW;
        }
        return RUNNING;
    }

    private ArrayList<Integer> getPlayerMoves(char player) {
        ArrayList<Integer> playerMoves = new ArrayList<>();
        int i = player == PLAYER_X ? 0 : 1;
        for (; i < moves.size(); i = i + 2) {
            playerMoves.add(moves.get(i));
        }
        return playerMoves;
    }

    private boolean isInWinningMoves(ArrayList<Integer> playerMoves) {
        for (String winningMove : winningMoves) {
            int counter = 0;
            for (int playerMove : playerMoves) {
                if (winningMove.contains(String.valueOf(playerMove))) {
                    counter++;
                }
            }
            if (counter == 3) {
                return true;
            }
        }
        return false;
    }
}
