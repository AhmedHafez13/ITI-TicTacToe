package com.tictactoe.actions;

import com.tictactoe.models.Game;
import com.tictactoe.models.LogAction;
import com.tictactoe.models.Player;
import com.tictactoe.server.App;
import com.tictactoe.server.DBManager;
import com.tictactoe.server.PlayerHandler;
import com.tictactoe.server.ServerManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

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
     * Try to login a player with username and password, then sends the login
     * result to the client (success or fail)
     *
     * @param data received from the client (expects: username and password)
     * @param playerHandler the handler that received the data
     */
    public void handleLogin(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleLogin, player:"
                + playerHandler.getId() + ", Data:");
        App.appendActionToLog(new LogAction("<<<Action Handler", "Handling login"));

        String username = data.get("username");
        String password = data.get("password");
        Player player = DBManager.signInPlayer(username, password);
        if (player == null) {
            App.appendActionToLog(new LogAction("<<Action Handler", "Login fail"));

            actionController.messageCreator.sendLoginFailed("No username, please try again!", playerHandler);
        } else {
            App.appendActionToLog(new LogAction("<<Action Handler", "Login success", player));

            playerHandler.setPlayer(player);
            // Notify all players
            actionController.messageCreator.sendLoginSuccess(
                    player, playerHandler);
            broadcastPlayersList();
        }
    }

    /**
     * Register a new player
     *
     * @param data received from the client (expects: username and password and
     * password confirmation)
     * @param playerHandler the handler that received the data
     */
    public void handleRegister(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleRegister, Data: " + data.toString());
        App.appendActionToLog(new LogAction("<<Action Handler", "Handling register"));

        boolean player = DBManager.registerNewPlayer(data.get("username"), data.get("password"), data.get("avatar"));
        if (player) {
            App.appendActionToLog(new LogAction("<<Action Handler", "Register failed"));

            actionController.messageCreator.sendRegisterSuccess(playerHandler);
        } else {
            App.appendActionToLog(new LogAction("<<Action Handler", "Register success"));

            actionController.messageCreator.sendRegisterFailed("Failed to Register your data!", playerHandler);
        }
    }

    public void handlePlayersList(HashMap<String, String> data, PlayerHandler playerHandler) {
        //      id,   Handler id
        HashMap<Integer, String> idsMap = getIdsMap();
        LinkedList<Player> players = DBManager.getPlayersSortedByStatus();
        String allPlayersStr = getPlayersStr(idsMap, players);
        actionController.messageCreator.sendPlayersList(
                allPlayersStr, playerHandler);
    }

    public void broadcastPlayersList() {
        App.appendActionToLog(new LogAction("Action Handler", "Broadcasting Players List"));
        //      id,   Handler id
        HashMap<Integer, String> idsMap = getIdsMap();
        LinkedList<Player> players = DBManager.getPlayersSortedByStatus();

        actionController.serverManager.refreshPlayersTables(players);

        String allPlayersStr = getPlayersStr(idsMap, players);
        HashMap<String, PlayerHandler> playersHandler
                = actionController.serverManager.getOnlinePlayersHandlers();
        Iterator<PlayerHandler> iterator = playersHandler.values().iterator();
        while (iterator.hasNext()) {
            PlayerHandler playerHandler = iterator.next();
            if (playerHandler.getPlayer() != null) {
                actionController.messageCreator.sendPlayersList(
                        allPlayersStr, playerHandler);
            }
        }
    }

    private HashMap<Integer, String> getIdsMap() {
        HashMap<Integer, String> idsMap = new HashMap<>();
        HashMap<String, PlayerHandler> onlinePlayers
                = actionController.serverManager.getOnlinePlayersHandlers();
        Iterator<String> iterator = onlinePlayers.keySet().iterator();
        while (iterator.hasNext()) {
            String currentHandlerId = iterator.next();
            PlayerHandler currentHandler = onlinePlayers.get(currentHandlerId);
            Player currentPlayer = currentHandler.getPlayer();
            if (currentPlayer != null) {
                idsMap.put(currentHandler.getPlayer().getId(), currentHandlerId);
            }
        }
        return idsMap;
    }

    private String getPlayersStr(HashMap<Integer, String> idsMap, LinkedList<Player> players) {
        char separator = ':';
        StringBuilder allPlayersStr = new StringBuilder();
        for (int i = 0; i < players.size(); i++) {
            //handlerId:name:totalScore:avatar:isOnline
            Player player = players.get(i);
            String handlerId = player.isOnline() ? idsMap.get(player.getId()) : "";
            String playerStr = handlerId + separator + player.getName() + separator
                    + player.getTotalScore() + separator + player.getAvatar()
                    + separator + player.isOnline() + "\n";
            allPlayersStr.append(playerStr);
        }
        return allPlayersStr.toString();
    }

    /**
     * Add a new move to a current playing game, then send the new moves to the
     * players
     *
     * @param data received from the client (expects: gameId and move index)
     * @param playerHandler the handler that received the data
     */
    public void handleMove(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleMove, Data: " + data.toString());

        App.appendActionToLog(new LogAction("<<<Action Handler", "Handling move",
                playerHandler.getPlayer()));

        String gameId = data.get("gameId");
        String movesIndex = data.get("index");
        String playWith = data.get("playWith");

        Game game = actionController.serverManager.getGame(gameId);

        if (game != null) {
            String gameMovesStr = game.setNextMove(movesIndex).toString()
                    .replaceAll("[\\[\\]\\s]", "");
            char gameResult = game.getGameResult();

            if (gameResult != Game.RUNNING) { // The game still alive
                PlayerHandler winnerHandler = game.getWinnerPlayerHandler(); // winner
                PlayerHandler loserHandler = game.getLoserPlayerHandler();  // loser

                winnerHandler.resetInivitationData();
                loserHandler.resetInivitationData();

                DBManager.updatePlayerScore(winnerHandler.getPlayer().getId(), 100);
                DBManager.insertNewGame(game.getPlayerX().getPlayer().getId(),
                        game.getPlayerO().getPlayer().getId(),
                        gameResult, gameMovesStr);

                actionController.serverManager.removeGame(gameId);

                actionController.messageCreator.sendGameMoves(
                        gameMovesStr, gameResult, false,
                        "Congratulations, You win :)", winnerHandler);
                actionController.messageCreator.sendGameMoves(gameMovesStr, gameResult, false,
                        "Unfortunately, You lose :(", loserHandler);
            } else {
                String[] messages = {"Play, it's you turn", "Waiting the oppenent to play..."};
                actionController.messageCreator.sendGameMoves(
                        gameMovesStr, gameResult, playWith.equalsIgnoreCase("O"),
                        playWith.equalsIgnoreCase("O") ? messages[0] : messages[1],
                        game.getPlayerX());
                actionController.messageCreator.sendGameMoves(
                        gameMovesStr, gameResult, playWith.equalsIgnoreCase("X"),
                        playWith.equalsIgnoreCase("X") ? messages[0] : messages[1],
                        game.getPlayerO());
            }
        } else {
            System.out.println("-----\n@ActionHandler->handleMove, "
                    + "Try to get a non existing game");
        }
    }

    public void handleCloseGame(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleCloseGame, Data: "
                + data.toString());

        App.appendActionToLog(new LogAction("<<<Action Handler", "Handling Close Game",
                playerHandler.getPlayer()));

        String opponentHandlerId = data.get("opponentId");
        String gameId = data.get("gameId");

        if (opponentHandlerId == null || gameId == null) {
            return;
        }

        PlayerHandler opponentHandler = actionController.serverManager
                .getPlayerHandler(opponentHandlerId);
        /*
         * TODO: 
         * the opponent should win the game (opponentHandler)
         * TODO: give the winner bonus points
         * Record the game in the database
         */
        playerHandler.resetInivitationData();
        opponentHandler.resetInivitationData();
        actionController.serverManager.removeGame(gameId);

        actionController.messageCreator.sendGameClose(opponentHandler);
    }

    /**
     * Receive invitation from a player and send it to the opponent player
     *
     * @param data received from the client (expects: opponent Id)
     * @param playerHandler the handler that received the data
     */
    public void handleGameInvitation(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleGameInvitation, Data: " + data.toString());

        App.appendActionToLog(new LogAction("<<<Action Handler", "Handling game invitation",
                playerHandler.getPlayer()));

        String opponentHandlerId = data.get("opponentId");

        PlayerHandler opponentHandler = actionController.serverManager.getPlayerHandler(opponentHandlerId);

        if (playerHandler.invitationTo == null && playerHandler.invitationFrom == null
                && opponentHandler.invitationTo == null && opponentHandler.invitationFrom == null) {
            String playerName = playerHandler.getPlayer().getName();
            String handlerId = playerHandler.getHandlerId();

            playerHandler.invitationTo = opponentHandlerId;
            opponentHandler.invitationFrom = handlerId;

            actionController.messageCreator.sendInvitation(playerName, handlerId, opponentHandler);
        }
    }

    /**
     * Receive response from the invited player. If the player refuses, send
     * refuse response to the other player. If the player accept, create a new
     * Game and add it to the currentGames map then send the game id to both
     * players
     *
     * @param data received from the client (expects: response[accept|refuse])
     * @param playerHandler the handler that received the data
     */
    public void handleGameInvitationResponse(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleGameInvitationResponse, Data: " + data.toString());

        App.appendActionToLog(new LogAction("<<<Action Handler", "Handling invitation response",
                playerHandler.getPlayer()));

        String response = data.get("response");
        String opponentId = playerHandler.invitationFrom;

        PlayerHandler opponentHandler
                = actionController.serverManager.getPlayerHandler(opponentId);

        if (response.equalsIgnoreCase("refuse")) {
            playerHandler.resetInivitationData();
            opponentHandler.resetInivitationData();
            actionController.messageCreator.sendRefuseInvitation(opponentHandler);
        } else if (response.equalsIgnoreCase("accept")) {
            String gameId = actionController.serverManager
                    .startNewGame(opponentHandler, playerHandler);
            actionController.messageCreator.sendAcceptInvitation(
                    gameId, opponentHandler, playerHandler);
        }
    }

}
