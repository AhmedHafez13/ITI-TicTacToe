package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import com.tictactoe.models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;

/**
 *
 * AppManager class manages whole the client's data
 */
public class AppManager {

    private final String IP = "127.0.0.1";
    private final int PORT = 5005;

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    ActionController actionController;

    Player playerData;

    LinkedList<Player> players = new LinkedList<>();
    String gameId = null;
    String playWith;
    String opponentName = null;

    private boolean isConnected;

    public AppManager() {
        actionController = new ActionController(this);
        try {
            socket = new Socket(IP, PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(socket.getOutputStream());
            isConnected = true;
        } catch (IOException ex) {
            isConnected = false;
            ex.printStackTrace();
        }
    }

    void listenToMessages() {
        new Thread(() -> {
            while (isConnected) {
                try {
                    String message = bufferedReader.readLine();
                    System.out.println("<<< Client just recieved this > " + message);
                    //{"data":{"registerResult":"success"},"action":"REGISTER"}
                    actionController.handleAction(message);
                } catch (IOException e) {
                    e.printStackTrace();
                    isConnected = false;
                }
            }
        }).start();
        // TODO: try to reconnect
    }

    public void sendMessage(String jsonMessage) {
        System.out.println(">>> Message before leaving the client:" + jsonMessage);
        if (isConnected) {
            printStream.println(jsonMessage);
        } else {
            System.out.println("@sendMessage, trying to send messag... No Connection!");
        }
    }

    public String getGameId() {
        return gameId;
    }

    public void setPlayerData(Player playerData) {
        this.playerData = playerData;
    }

    public Player getPlayerData() {
        return playerData;
    }

    public void setGameData(String gameId, String playWith) {
        this.gameId = gameId;
        this.playWith = playWith;
    }

    public String getPlayWith() {
        return playWith;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public void setPlayersList(LinkedList<Player> players) {
        this.players = players;
    }

    public LinkedList<Player> getPlayersList() {
        return players;
    }

    public void requestPlayersList() {
        actionController.getMessageCreator().requestPlayersList();
    }
}
