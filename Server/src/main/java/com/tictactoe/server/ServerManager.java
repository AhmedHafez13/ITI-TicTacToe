package com.tictactoe.server;

import com.tictactoe.actions.ActionController;
import com.tictactoe.models.Game;
import com.tictactoe.models.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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
            iterator.next().closeConnection();;
        }
        onlinePlayers = new HashMap<>();
        currentGames = new HashMap<>();
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

    void initializePlayersTables() {
        System.out.println("-----\n@ServerManager->initializePlayersTables, listing players...");

        Scene serverScene = App.getScene();
        TableView onlinePlayersTable = (TableView) serverScene.lookup("#onlinePlayersTable");
        TableView offlinePlayersTable = (TableView) serverScene.lookup("#offlinePlayersTable");

        LinkedList<Player> allPlayers = DBManager.getPlayersSortedByStatus();

        for (Player player : allPlayers) {
            if (player.isOnline()) {
                onlinePlayersTable.getItems().add(player);
            } else {
                offlinePlayersTable.getItems().add(player);
            }
        }
    }

}
