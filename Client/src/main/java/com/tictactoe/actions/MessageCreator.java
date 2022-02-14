package com.tictactoe.actions;

import java.util.HashMap;

/**
 *
 *
 */
public class MessageCreator {

    private final ActionController actionController;

    MessageCreator(ActionController actionController) {
        this.actionController = actionController;
    }

    /**
     *
     * @param username
     * @param password
     */
    public void sendLogin(String username, String password) {
        System.out.println("@MessageCreator->sendLogin, username:" + username + ", password:" + password);
        actionController.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
            }
        });
    }

    /**
     *
     * @param username
     * @param password
     * @param confirmPassword
     */
    public void sendRegister(String username, String password, String confirmPassword) {
        actionController.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
                put("confirmPassword", confirmPassword);
            }
        });
    }

    /**
     *
     * @param moveIndex
     */
    public void sendMove(String moveIndex) {
        actionController.sendAction(Message.GAME_MOVE, new HashMap<String, String>() {
            {
                put("moveIndex", moveIndex);
            }
        });
    }
}
