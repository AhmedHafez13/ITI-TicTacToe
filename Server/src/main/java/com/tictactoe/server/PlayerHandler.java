package com.tictactoe.server;

import com.tictactoe.actions.ActionController;
import com.tictactoe.models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.UUID;

/**
 *
 *
 */
public class PlayerHandler extends Thread {

    private BufferedReader bufferedReader;
    private PrintStream printStream;

    private ServerManager serverManager;

    private Player player = null;
    String id;
    String gameId = null;

    private boolean isConnected;

    public PlayerHandler(ServerManager serverManager) {
        this.serverManager = serverManager;
        id = UUID.randomUUID().toString();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(
                    serverManager.getSocket().getInputStream()));
            printStream = new PrintStream(
                    serverManager.getSocket().getOutputStream());
            serverManager.addPlayerHandler(this);
            isConnected = true;
            start();
        } catch (IOException e) {
            isConnected = false;
            e.printStackTrace();
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        while (isConnected) {
            try {
                String message = bufferedReader.readLine();
                System.out.println(message);
                serverManager.actionController.handleAction(message, this);
            } catch (IOException e) {
                e.printStackTrace();
                serverManager.removePlayerHandler(this);
                isConnected = false;
            }
        }
    }

    public void sendAction(String action, HashMap<String, String> data) {
        String jsonMessage = ActionController.createActionJson(action, data);
        if (isConnected) {
            printStream.println(jsonMessage);
        } else {
            System.out.println("@PlayerHandler->sendMessage, trying to send messag... No Connection!");
        }
    }

    public String getGameId() {
        return gameId;
    }
}
