package com.tictactoe.actions;

import com.tictactoe.server.PlayerHandler;
import java.util.HashMap;

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
                put("result", "failed");
                put("userMessage", message);
            }
        });
    }
}
