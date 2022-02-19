package com.tictactoe.actions;

import com.tictactoe.models.Game;
import com.tictactoe.models.Player;
import com.tictactoe.server.DBManager;
import com.tictactoe.server.PlayerHandler;
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
        System.out.println(Arrays.toString(data.values().toArray()));

        String username = data.get("username");
        String password = data.get("password");
        Player player = DBManager.signInPlayer(username, password);
        if (player == null) {
            actionController.messageCreator.sendLoginFailed("No username, please try again!", playerHandler);
        } else {
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

        boolean player = DBManager.registerNewPlayer(data.get("username"), data.get("password"), data.get("avatar"));
        if (player) {
            actionController.messageCreator.sendRegisterSuccess(playerHandler);
        } else {
            actionController.messageCreator.sendRegisterFailed("Failed to Register your data!", playerHandler);
        }
    }

    public void handlePlayersList(HashMap<String, String> data, PlayerHandler playerHandler) {
        //      id,   Handler id
        HashMap<Integer, String> idsMap = getIdsMap();
        String allPlayersStr = getPlayersStr(idsMap);
        actionController.messageCreator.sendPlayersList(
                allPlayersStr, playerHandler);
    }

    public void broadcastPlayersList() {
        //      id,   Handler id
        HashMap<Integer, String> idsMap = getIdsMap();
        String allPlayersStr = getPlayersStr(idsMap);
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

    private String getPlayersStr(HashMap<Integer, String> idsMap) {
        LinkedList<Player> players = DBManager.getPlayersSortedByStatus();
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

        String gameId = data.get("gameId");
        String movesIndex = data.get("index");
        Game game = actionController.serverManager.getGame(gameId);
        if (game != null) {
            ArrayList<Integer> newMoves = game.setNextMove(movesIndex);
            boolean gameOver = game.isGameOver();

            if (gameOver) {
                game.getPlayerX().resetInivitationData();
                game.getPlayerO().resetInivitationData();
                actionController.messageCreator.sendGameEnd(game.getWinnerId(),
                        playerHandler, playerHandler);
                actionController.serverManager.removeGame(gameId);
                /*
                 * TODO: give the winner bonus points
                 * Record the game in the database
                 */
            } else {
                String gameMovesStr = newMoves.toString()
                        .replaceAll("[\\[\\]\\s]", "");
                actionController.messageCreator.sendGameMoves(
                        gameMovesStr, game.getPlayerX(), game.getPlayerO());
            }

        } else {
            System.out.println("-----\n@ActionHandler->handleMove, "
                    + "Try to get a non existing game");
        }
    }

    /**
     * Receive invitation from a player and send it to the opponent player
     *
     * @param data received from the client (expects: opponent Id)
     * @param playerHandler the handler that received the data
     */
    public void handleGameInvitation(HashMap<String, String> data, PlayerHandler playerHandler) {
        System.out.println("-----\n<<@ActionHandler->handleGameInvitation, Data: " + data.toString());

        String opponentHandlerId = data.get("opponentId");

        PlayerHandler opponentHandler = actionController.serverManager.getPlayerHandler(opponentHandlerId);

        String playerName = playerHandler.getPlayer().getName();
        String handlerId = playerHandler.getHandlerId();

        playerHandler.invitationTo = opponentHandlerId;
        opponentHandler.invitationFrom = handlerId;

        actionController.messageCreator.sendInvitation(playerName, handlerId, opponentHandler);
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
                    gameId, playerHandler, opponentHandler);
        }
    }
}
