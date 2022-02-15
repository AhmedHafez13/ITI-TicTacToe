package com.tictactoe.actions;

import java.util.Arrays;
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
        System.out.println("@ActionHandler->handleLogin, Data:"
                + Arrays.toString(data.values().toArray()));
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
        String playerId = data.get("playerId");
        String playerName = data.get("playerName");

        /*TODO:
         * • Show invitation message with (playerName)
         */
    }

    public void handleGameStart(HashMap<String, String> data) {
        String gameId = data.get("gameId");
        String opponentName = data.get("opponentName");
        
        /*TODO:
         * • Set game id
         * • show game scene and set opponentName
         */
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
