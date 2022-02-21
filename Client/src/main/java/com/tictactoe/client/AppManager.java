package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import com.tictactoe.actions.MessageCreator;
import com.tictactoe.models.Player;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.LinkedList;
import javafx.application.Platform;
import javafx.scene.control.Label;

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

    public static final String GAME_TYPE_PC = "GAME_TYPE_PC";
    public static final String GAME_TYPE_MP = "GAME_TYPE_MULTIPLAYER";

    public static String GameLevel;
    public static String GameType;

    ActionController actionController;

    Player playerData;

    LinkedList<Player> players = new LinkedList<>();
    String gameId = null;
    String playWith; // X | O
    String opponentName = null;

    private boolean isConnected;

    public AppManager() {
        actionController = new ActionController(this);
        openConnection();
    }

    void openConnection() {
        try {
            socket = new Socket(IP, PORT);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(socket.getOutputStream());
            isConnected = true;
            System.out.println("-----\n@AppManager->openConnection, Connected to the server");
        } catch (IOException ex) {
            isConnected = false;
            System.out.println("-----\n@AppManager->openConnection, Connection lost!");
            ex.printStackTrace();
        }
    }

    void listenToMessages() {
        new Thread(() -> {
            while (isConnected) {
                try {
                    String message = bufferedReader.readLine();
                    System.out.println("<<< Client just recieved this > " + message);
                    if (message != null) {
                        actionController.handleAction(message);
                    } else {
                        System.out.println("<< Reseived null?!");
                        throw new IOException();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    isConnected = false;
                    System.out.println("-----\n@AppManager->listenToMessages, Connected to the server");
                    onDisconnect();
                }
            }
        }).start();
    }

    public void sendMessage(String jsonMessage) {
        if (isConnected) {
            printStream.println(jsonMessage);
        } else {
            // Try to reconnect
            System.out.println("@sendMessage, No Connection! trying to reconnect...");
            openConnection();
            if (isConnected) {
                System.out.println(">>> Message before leaving the client:" + jsonMessage);
                printStream.println(jsonMessage);
            } else {
                System.out.println("@sendMessage, No Connection! Couldn't send the message");
            }
        }
    }

    private void onDisconnect() {
        Platform.runLater(() -> {
            try {
                App.setRoot("LoginScene");
                Label messageLabel = (Label) App.getScene().lookup("#registerMsgLogin");
                if (messageLabel != null) {
                    messageLabel.setText("Disconnected!!! :(");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        resetGameData();
    }

    public void handleInvite(String playerId) {
        System.out.println("-----\n@AppManager->handleInivte, playerId:" + playerId);
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendInvitation(playerId);
    }

    public void closeCurrentGame() {
        // Send close game to the server
        if (gameId != null) {
            MessageCreator messageCreator = actionController.getMessageCreator();
            messageCreator.sendGameClose(gameId, playWith);

            // Reset the game data
            resetGameData();
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
        AppManager.GameType = GAME_TYPE_MP;
    }

    public void resetGameData() {
        gameId = null;
        playWith = null;
        opponentName = null;
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
