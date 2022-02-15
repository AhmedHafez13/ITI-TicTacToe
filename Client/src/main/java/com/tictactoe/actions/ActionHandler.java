package com.tictactoe.actions;

import java.util.HashMap;

/**
 *
 *
 */
public class ActionHandler {

    private final ActionController actionController;

    ActionHandler(ActionController actionController) {
        this.actionController = actionController;
    }

    /**
     *
     * @param data
     */
    public void handleLogin(HashMap<String, String> data) {
        /*TODO:
         * if can login =>
         * • Show players list
         * if can't login =>
         * • Show error message
         */
    }

    /**
     *
     * @param data
     */
    public void handleRegister(HashMap<String, String> data) {
        /*TODO:
         * if can register =>
         * • (the same as handleLogin)
         * if can't regisgter =>
         * • Show error message
         */
    }

    /**
     *
     * @param data
     */
    public void handleMove(HashMap<String, String> data) {
        /*TODO:
         * • Apply the new move
         */
    }


    public void handleGameInvitation(HashMap<String, String> data) {
        /*TODO:
         * • Show invitation message
         */
    }

    /* ----- * ----- * ----- * ----- * ----- */
    /* ----- * - * Send Messages * - * ----- */
    /* ----- * ----- * ----- * ----- * ----- */

    /**
     *
     * @param username
     * @param password
     */
    public void sendLogin(String username, String password) {
            
        actionController.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
            }
        });
            //System.out.println(username+":"+password);
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
