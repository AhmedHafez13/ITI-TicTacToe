package com.tictactoe.server;

import com.tictactoe.models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.UUID;

/**
 *
 *
 */
public class PlayerHandler extends Thread {

    private BufferedReader bufferedReader;
    private PrintStream printStream;

    private ServerManager serverManager;

    String id;
    private Player player = null;
    String gameId = null;

    private boolean isConnected;

    public PlayerHandler(ServerManager serverManager) {
        this.serverManager = serverManager;
        id = UUID.randomUUID().toString();

        try {
            bufferedReader = new BufferedReader(new InputStreamReader(serverManager.getSocket().getInputStream()));
            printStream = new PrintStream(serverManager.getSocket().getOutputStream());
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

    public String getGameId() {
        return gameId;
    }
}
