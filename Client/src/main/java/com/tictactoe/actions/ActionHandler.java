package com.tictactoe.actions;

import com.tictactoe.client.App;
import com.tictactoe.client.AppManager;
import com.tictactoe.models.Player;
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
        System.out.println("-----\n<<@ActionHandler->handleLogin, Data:"
                + data.toString());
        String loginResult = data.get("loginResult");
        if (loginResult.equalsIgnoreCase("success")) {
            String handlerId = data.get("handlerId");
            String playerName = data.get("playerName");
            int playerScore = Integer.parseInt(data.get("playerScore"));
            String playerAvatar = data.get("playerAvatar");

            Player playerData = new Player(handlerId, playerName,
                    playerScore, playerAvatar);

            appManager.setPlayerData(playerData);
            App.getSceneManager().showMainMenu(playerData);
        } else {
            App.getSceneManager().showLoginFailed("Login failed");
        }
    }

    /**
     *
     * @param data
     */
    public void handleRegister(HashMap<String, String> data) {
        System.out.println("-----\n<<@ActionHandler->handleRegister, Data:"
                + data.toString());

        String registerResult = data.get("registerResult");
        App.getSceneManager().registerMessageToUI(registerResult);
    }

    public void handlePlayersList(HashMap<String, String> data) {
        System.out.println("-----\n<<@ActionHandler->handlePlayersList, Data:"
                + data.toString());
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
            if (!handlerId.equalsIgnoreCase(appManager.getPlayerData().getHandlerId())) {
                players.add(new Player(handlerId, name, totalScore, avatar));
            }
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
        System.out.println("-----\n<<@ActionHandler->handleMove, Data:"
                + data.toString());

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
        System.out.println("-----\n<<@ActionHandler->handleGameInvitation, Data:"
                + data.toString());
        String playerId = data.get("playerId");
        String playerName = data.get("playerName");
        App.getSceneManager().showInvitationPopUp(playerName, playerId);

    }

    public void handleGameInvitationResponse(HashMap<String, String> data) {
        System.out.println("-----\n<<@ActionHandler->handleGameInvitationResponse, Data:"
                + data.toString());
        String Response = data.get("response");
        if (Response.equalsIgnoreCase("accept")) {
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
        System.out.println("-----\n<<@ActionHandler->handleGameStart, Data:"
                + data.toString());

        String gameId = data.get("gameId");
        String opponentName = data.get("opponentName");
        boolean startTheGame = Boolean.getBoolean(data.get("startTheGame"));

        appManager.setGameId(gameId);

        App.sceneManager.showGameScene(appManager.getPlayerData().getName(),
                opponentName, startTheGame);
    }

    /**
     *
     * @param data received from the client (expects: game moves in this format
     * "5,6,4,8,7")
     */
    public void handleGameMove(HashMap<String, String> data) {
        System.out.println("-----\n<<@ActionHandler->handleGameMove, Data:"
                + data.toString());

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
        System.out.println("-----\n<<@ActionHandler->handleGameEnd, Data:"
                + data.toString());

        /*TODO:
         * • Show a message tells the winner, and (play again) button
         */
        // data -> winner, ...
    }
}
