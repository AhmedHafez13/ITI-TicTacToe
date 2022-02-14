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
        for (String value : data.values()) {
            System.out.println(value);
        }
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

    /**
     *
     * @param data
     */
    public void handleGameInvitation(HashMap<String, String> data) {
        /*TODO:
         * • Show invitation message
         */
    }
}
