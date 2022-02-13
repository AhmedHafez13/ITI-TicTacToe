/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tictactoe.server;

import com.tictactoe.actions.ActionController;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

/**
 *
 *
 */
public class ServerManager {

    private ServerSocket serverSocket;
    private Socket socket;

    ActionController actionController;

    HashMap<String, PlayerHandler> onlinePlayers = new HashMap<>();

    // [START] DEV
    int idCounter = -1;
    // [END] DEV

    public ServerManager() {
        actionController = new ActionController(this);
        try {
            serverSocket = new ServerSocket(5005);
            while (true) {
                socket = serverSocket.accept();

                addPlayerHandler(new PlayerHandler(this));
            }
        } catch (IOException ex) {
            // TODO: Show error message to the user
            ex.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Socket getSocket() {
        return this.socket;
    }

    public void addPlayerHandler(PlayerHandler playerHander) {
        onlinePlayers.put(playerHander.id, playerHander);
    }

    public void removePlayerHandler(PlayerHandler playerHandler) {
        onlinePlayers.remove(playerHandler.id);
    }

    public void removePlayerHandler(String playerHandlerId) {
        onlinePlayers.remove(playerHandlerId);
    }

    public PlayerHandler getPlayerHandler(int playerHandlerId) {
        return onlinePlayers.getOrDefault(playerHandlerId, null);
    }
}
