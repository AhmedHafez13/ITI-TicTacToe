package com.tictactoe.actions;

import com.tictactoe.client.AppManager;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 *
 */
public class ActionHandler {

    private final ActionController actionController;
    private final AppManager appManager;

    ActionHandler(ActionController actionController) {
        this.actionController = actionController;
        appManager = actionController.appManager;
    }

    /**
     *
     * @param data
     */
    public void handleLogin(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleLogin, Data:"
                + Arrays.toString(data.values().toArray()));
        /*TODO:
         * if can login =>
         * • Show players list
         * if can't login =>
         * • Show error message
         */
        System.out.println("@ActionHandler->handleLogin, Data:"
                + Arrays.toString(data.values().toArray()));
        String loginResult = data.get("loginResult");
        if (loginResult.equalsIgnoreCase("success")) {
            System.out.println("Login success");
        } else {
            System.out.println("Login failed");
        }

    }

    /**
     *
     * @param data
     */
    public void handleRegister(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleRegister, Data:"
                + Arrays.toString(data.values().toArray()));
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
        System.out.println("-----\n@ActionHandler->handleGameInvitation, Data:"
                + Arrays.toString(data.values().toArray()));

        String playerId = data.get("playerId");
        String playerName = data.get("playerName");

        /*TODO:
         * • Show invitation message with (playerName)
         */
    }

    /**
     *
     * @param data
     */
    public void handleGameStart(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleGameStart, Data:"
                + Arrays.toString(data.values().toArray()));

        String gameId = data.get("gameId");
        String opponentName = data.get("opponentName");

        /*TODO:
         * • Set game id
         * • show game scene and set opponentName
         */
    }

    /**
     *
     * @param data
     */
    public void handleGameMove(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleGameMove, Data:"
                + Arrays.toString(data.values().toArray()));

        /*TODO:
         * • Apply the new move
         */
    }

    /**
     *
     * @param data
     */
    public void handleGameEnd(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleGameEnd, Data:"
                + Arrays.toString(data.values().toArray()));

        /*TODO:
         * • Show a message tells the winner, and (play again) button
         */
        // data -> winner, ...
    }

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

}
