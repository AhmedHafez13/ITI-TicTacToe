package com.tictactoe.actions;

import com.tictactoe.client.App;
import com.tictactoe.client.AppManager;
import com.tictactoe.models.Player;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

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
        String loginResult = data.get("loginResult");
        if (loginResult.equalsIgnoreCase("success")) {
            App.getSceneManager().showMainMenu();
        } else {
            App.getSceneManager().showLoginFailed("Login failed");
        }
    }

    /**
     *
     * @param data
     */
    public void handleRegister(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleRegister, Data:"
                + Arrays.toString(data.values().toArray()));

        String registerResult = data.get("registerResult");
        App.getSceneManager().registerMessageToUI(registerResult);
        /*TODO:
         * if can register =>
         * • (the same as handleLogin)
         * if can't regisgter =>
         * • Show error message
         */
    }

    public void handlePlayersList(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handlePlayersList, Data:"
                + Arrays.toString(data.values().toArray()));
        String allPlayersStr = data.get("players");
        String separator = ":";

        LinkedList<Player> players = new LinkedList<>();

        //handlerId:name:totalScore:avatar:isOnline
        String[] playersStr = allPlayersStr.split("\n");
        for (String player : playersStr) {
            String[] parts = player.split(separator);

            String handlerId = parts[0];
            String name = parts[1];
            int totalScore = Integer.parseInt(parts[2]);
            String avatar = parts[3];
            //boolean isOnline = Boolean.valueOf(parts[4]);

            players.add(new Player(handlerId, name, totalScore, avatar));
        }

        // DATA
        appManager.setPlayersList(players);
        // UI
        App.getSceneManager().listPlayers(players);
    }

    /**
     *
     * @param data
     */
    public void handleMove(HashMap<String, String> data) {

        actionController.sendAction(Message.GAME_MOVE, new HashMap<String, String>() {
            {
                put("gameId", data.get("gameId"));
                put("index", data.get("index"));
            }
            ////////////////////can use one HashMap?///// 
        });

        /*TODO:
         * • Apply the new move
         */
    }

    public void handleGameInvitation(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleGameInvitation, Data:" + Arrays.toString(data.values().toArray()));
        String playerId = data.get("playerId");
        String playerName = data.get("playerName");
        App.getSceneManager().showInvitationPopUp(playerName, playerId);

    }

    public void handleGameInvitationResponse(HashMap<String, String> data) {
        System.out.println("-----\n@ActionHandler->handleGameInvitationResponse, Data:"
                + Arrays.toString(data.values().toArray()));
        String Response = data.get("response");
        if (Response.equalsIgnoreCase("Accepted")) {
            App.getSceneManager().InvitationMessageToUI("Accepted");

        } else {
            App.getSceneManager().InvitationMessageToUI("Refused");

        }
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

        // "5,6,4,8,7" -> data.get("gameMoves")  
        /*TODO:
         * • Apply the new move
         */
        String gameMoves[] = data.get("gameMoves").split(",");
        int[] moves = new int[gameMoves.length];

        for (int i = 0; i < gameMoves.length; i++) {
            moves[i] = Integer.parseInt(gameMoves[i]);
        }
        App.getSceneManager().gameMovesToUI(moves);
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
}
