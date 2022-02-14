package com.tictactoe.actions;

import com.tictactoe.server.PlayerHandler;
import java.util.Arrays;
import java.util.HashMap;

/**
 *
 *
 */
public class ActionHandler {

    private ActionController actionController;

    ActionHandler(ActionController actionController) {
        this.actionController = actionController;
    }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleLogin(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("@ActionHandler->handleLogin, player:"
                + playerHandler.getId() + ", Data:");
        System.out.println(Arrays.toString(data.values().toArray()));

        actionController.messageCreator.sendLoginFailed(
                "No username, please try again!", playerHandler);

        /*
         * TODO:
         * if can login =>
         * • create a new Player instance and put it in the playerHandler
         * • send back a message with (login) action (success)
         * if can't login =>
         * • send back a message with (login) action (failed)
         */
    }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleRegister(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*
         * TODO:
         * if can register =>
         * • login the player (the same as handleLogin)
         * • send back a message with (register) action (success)
         * if can't regisgter =>
         * • send back a message with (register) action (failed)
         */
        System.out.println("@ActionHandler->handleRegister, Data: "
                + Arrays.toString(data.values().toArray()));
    }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleMove(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*
         * TODO:
         * • Add the received move to the moves array
         * • send the new move to the opponent
         */
    }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleGameInvitation(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*
         * TODO:
         * • Add the received move to the moves array
         * • send the new move to the opponent
         */
    }
}
