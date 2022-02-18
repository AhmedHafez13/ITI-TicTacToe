package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import com.tictactoe.models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * AppManager class manages whole the client's data
 */
public class AppManager {

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    ActionController actionController;

    ArrayList<Player> players = null;
    String gameId = null;

    private boolean isConnected;

    public AppManager() {
        actionController = new ActionController(this);
        try {
            socket = new Socket("127.0.0.1", 5005);
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
                    System.out.println("Client just recieved this > " + message);
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
        System.out.println("Message before leaving the client:" + jsonMessage);
        if (isConnected) {
            printStream.println(jsonMessage);
        } else {
            System.out.println("@sendMessage, trying to send messag... No Connection!");
        }
    }

    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayersList(ArrayList<Player> players) {
        this.players = players;
    }

    public ArrayList<Player> getPlayersList() {
        return players;
    }

    public void requestPlayersList() {

    }
}
