package com.tictactoe.server;

import com.tictactoe.actions.ActionController;
import com.tictactoe.models.Game;
import com.tictactoe.models.Player;
import static com.tictactoe.server.App.serverManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;
import javafx.scene.Scene;
import javafx.scene.control.TableView;

/**
 *
 *
 */
public class ServerManager {

    private final int PORT = 5005;

    private ServerSocket serverSocket;
    private Socket socket;
    private Thread serverThread;
    private boolean isServerRunning = false;

    ActionController actionController;

    HashMap<String, PlayerHandler> onlinePlayers = new HashMap<>();
    HashMap<String, Game> currentGames = new HashMap<>();

    public ServerManager() {
        DBManager.initializeDB();
        actionController = new ActionController(this);

        System.out.println("-----\n@ServerManager->ServerManager, Constructing ServerManager...");
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void addPlayerHandler(PlayerHandler playerHander) {
        onlinePlayers.put(playerHander.getHandlerId(), playerHander);
    }

    public void removePlayerHandler(PlayerHandler playerHandler) {
        onlinePlayers.remove(playerHandler.getHandlerId());
    }

    public void removePlayerHandler(String playerHandlerId) {
        onlinePlayers.remove(playerHandlerId);
    }

    public PlayerHandler getPlayerHandler(String playerHandlerId) {
        return onlinePlayers.getOrDefault(playerHandlerId, null);
    }

    /**
     * Find and return a current playing game by id
     *
     * @param gameId the id of the game need to be returned
     * @return a game object from currentGames map, return null if the game was
     * not found
     */
    public Game getGame(String gameId) {
        return currentGames.get(gameId);
    }

    public void removeGame(String gameId) {
        currentGames.remove(gameId);
    }

    public HashMap<String, PlayerHandler> getOnlinePlayersHandlers() {
        return onlinePlayers;
    }

    boolean toggleServer() {
        if (isServerRunning) {
            stopServer();
        } else {
            startServer();
        }
        return isServerRunning;
    }

    private synchronized void startServer() {
        System.out.println("-----\n@ServerManager->startServer, Starting the server...");
        isServerRunning = true;
        startServerThread();
    }

    private synchronized void stopServer() {
        System.out.println("-----\n@ServerManager->startServer, Stopping the server...");
        if (serverThread == null) {
            return;
        }

        isServerRunning = false;

        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        serverThread = null;

        // Clean maps
        Iterator<PlayerHandler> iterator = onlinePlayers.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().closeConnection();
        }

        setAllPlayersOffline();
    }

    private synchronized void startServerThread() {
        if (serverThread != null) {
            //throw new IllegalStateException("The receiver is already started!");
            // The receiver is already started!
            return;
        }
        serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(PORT);
                isServerRunning = true;
                while (isServerRunning) {
                    socket = serverSocket.accept();
                    addPlayerHandler(new PlayerHandler(this));
                }
            } catch (SocketException e) {
                // TODO: Show error message to the user
                e.printStackTrace();
            } catch (IOException e) {
                // TODO: Show error message to the user
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                    serverSocket.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            System.out.println("-----\n@ServerManager->startServerThread, serverThread stopped!");
        });
        serverThread.start();
    }

    void setAllPlayersOffline() {
        DBManager.setAllPlayersOffline();
        refreshPlayersTables(DBManager.getPlayersSortedByStatus());
        onlinePlayers = new HashMap<>();
        currentGames = new HashMap<>();
    }

    public void refreshPlayersTables(LinkedList<Player> allPlayers) {
        System.out.println("-----\n@ServerManager->initializePlayersTables, listing players...");

        Scene serverScene = App.getScene();
        TableView onlinePlayersTable = (TableView) serverScene.lookup("#onlinePlayersTable");
        TableView offlinePlayersTable = (TableView) serverScene.lookup("#offlinePlayersTable");

        onlinePlayersTable.getItems().clear();
        offlinePlayersTable.getItems().clear();

        for (Player player : allPlayers) {
            if (player.isOnline()) {
                onlinePlayersTable.getItems().add(player);
            } else {
                offlinePlayersTable.getItems().add(player);
            }
        }
    }

    /**
     * Create a new Game and append it to currentGames
     *
     * @param player1 the first player who invited the second one
     * @param player2 the second player who accepted the invitation
     * @return the new game id
     */
    public String startNewGame(PlayerHandler player1, PlayerHandler player2) {
        String gameId = UUID.randomUUID().toString();
        Game newGame = new Game(player1, player2, gameId);
        currentGames.put(gameId, newGame);
        return gameId;
    }
}
