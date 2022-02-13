package com.tictactoe.actions;

import com.tictactoe.server.PlayerHandler;
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

    public void handleLogin(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*TODO:
         * if can login =>
         * • create a new Player instance and put it in the playerHandler
         * • send back a message with (login) action (success)
         * if can't login =>
         * • send back a message with (login) action (failed)
         */
    }

    public void handleRegister(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*TODO:
         * if can register =>
         * • login the player (the same as handleLogin)
         * • send back a message with (register) action (success)
         * if can't regisgter =>
         * • send back a message with (register) action (failed)
         */
    }

    public void handleMove(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*TODO:
         * • Add the received move to the moves array
         * • send the new move to the opponent
         */
    }

    public void handleGameInvitation(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*TODO:
         * • Add the received move to the moves array
         * • send the new move to the opponent
         */
    }

    /* ----- * ----- * ----- * ----- * ----- */
}
