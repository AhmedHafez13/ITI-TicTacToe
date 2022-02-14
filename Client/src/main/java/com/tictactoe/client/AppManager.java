package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 *
 *
 */
public class AppManager {

    private Socket socket;
    private BufferedReader bufferedReader;
    private PrintStream printStream;

    ActionController actionController;

    private boolean isConnected;

    public AppManager() {
        actionController = new ActionController(this);
        try {
            socket = new Socket("127.0.0.1", 5005);
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printStream = new PrintStream(socket.getOutputStream());
            isConnected = true;
            new Thread(() -> {

            }).start();
        } catch (IOException ex) {
            isConnected = false;
            ex.printStackTrace();
        }
    }

    private void onReceiveMessage() {
        while (isConnected) {
            try {
                String message = bufferedReader.readLine();
                System.out.println(message);
                actionController.handleAction(message);
            } catch (IOException e) {
                e.printStackTrace();
                isConnected = false;
            }
        }
        // TODO: try to reconnect
    }

    public void sendMessage(String jsonMessage) {
        if (isConnected) {
            printStream.println(jsonMessage);
        } else {
            System.out.println("@sendMessage, trying to send messag... No Connection!");
        }
    }

}
