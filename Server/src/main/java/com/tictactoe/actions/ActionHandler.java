package com.tictactoe.actions;

import com.tictactoe.models.Game;
import com.tictactoe.models.Player;
import com.tictactoe.server.DBManager;
import com.tictactoe.server.PlayerHandler;
import java.util.ArrayList;
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

        String username = data.get("username");
        String password = data.get("password");
        Player player = DBManager.signInPlayer(username, password);
        if (player == null) {
            actionController.messageCreator.sendLoginFailed("No username, please try again!", playerHandler);
        } else {
            ArrayList<String> arraylist = new ArrayList<>();
            playerHandler.setPlayer(player);
            arraylist.add("player1");
            arraylist.add("player2");
            actionController.messageCreator.sendLoginSuccess(arraylist, playerHandler);
        }


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
        System.out.println("@ServerActionHandler->handleRegister, Data: "+ Arrays.toString(data.values().toArray()));

        boolean player = DBManager.registerNewPlayer(data.get("username"), data.get("password"), null);
        if(player){
            //System.out.println("Player inserted successfully!");
            actionController.messageCreator.sendRegisterSuccess(playerHandler);
            //scene should be changed to login scene if the data were registered successfully!
        }else{
            //System.out.println("Couldn't insert the player into the database!");
            actionController.messageCreator.sendRegisterFailed("Failed to Register your data!", playerHandler);
            //alert should be appear to the user!
            }
        /*
         * TODO:
         * if can register =>
         * • login the player (the same as handleLogin)
         * • send back a message with (register) action (success)
         * if can't regisgter =>
         * • send back a message with (register) action (failed)
         */
    }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleMove(HashMap<String, String> data, PlayerHandler playerHandler) {
        String username = data.get("gameId");
        String moves = data.get("index");
//        Player player = DBManager.insertNewGame(int playerXId, int playerOId, char winner, moves);
        /*
         * TODO
         * • Add the received move to the moves array
         * • send the new move to the opponent
         */
        String gameId=data.get("gameId");
        String index=data.get("index");
   }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleGameInvitation(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*
         * TODO:
         * • send the invitation to the opponent
         */
        String opponentHandlerId = data.get("playerId");

        PlayerHandler opponentHandler = actionController.serverManager.getPlayerHandler(opponentHandlerId);

        String playerName = playerHandler.getPlayer().getName();
        String handlerId = playerHandler.getHandlerId();

        playerHandler.invitationTo = opponentHandlerId;
        opponentHandler.invitationFrom = handlerId;

        actionController.messageCreator.sendInvitation(playerName, handlerId, opponentHandler);
    }

    /**
     *
     * @param data
     * @param playerHandler
     */
    public void handleGameInvitationResponse(HashMap<String, String> data, PlayerHandler playerHandler) {
        /*
         * TODO:
         * • if accept: add new game into currentGames
         * • send game id to both players
         */
        //new Game();
        // response: accept | refuse
        // gameId
        // playerName
        // playerScore
    }
}
