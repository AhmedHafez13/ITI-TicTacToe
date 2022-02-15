package com.tictactoe.actions;

import com.tictactoe.models.Player;
import com.tictactoe.server.PlayerHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 *
 */
public class MessageCreator {

    private ActionController actionController;

    MessageCreator(ActionController actionController) {
        this.actionController = actionController;
    }

    public void sendLoginFailed(String message, PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendLoginFailed, message:" + message);
        playerHandler.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("loginResult", "failed");
                put("userMessage", message);
            }
        });
    }

    public void sendLoginSuccess(ArrayList<String> playerNames, PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendLoginSuccess");
    }
}
