package com.tictactoe.actions;

import com.tictactoe.models.Player;
import com.tictactoe.server.PlayerHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 *
 */
public class MessageCreator {

    private ActionController actionController;

    MessageCreator(ActionController actionController) {
        this.actionController = actionController;
    }

    public void sendLoginFailed(String message, PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendLoginFailed, message:" + message);

        HashMap data = new HashMap<String, String>() {
            {
                put("loginResult", "failed");
                put("userMessage", message);
            }
        };
        playerHandler.sendAction(Message.LOGIN, data);
    }

    public void sendLoginSuccess(PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendLoginSuccess");
        playerHandler.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("loginResult", "success");
            }
        });
    }

    public void sendRegisterSuccess(PlayerHandler playerHandler) {
        System.out.println("@ServerMessageCreator->sendRegisterSuccess");
        playerHandler.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("registerResult", "success");
            }
        });
    }

    public void sendRegisterFailed(String message, PlayerHandler playerHandler) {
        System.out.println("@ServerMessageCreator->sendRegisterFailed");

        playerHandler.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("registerResult", "duplicated");
            }
        });
    }

    public void sendPlayersList(String players, PlayerHandler playerHandler) {
        //
        System.out.println("@ServerMessageCreator->sendPlayersListOrderedByStatus");

        playerHandler.sendAction(Message.PLAYERS_LIST, new HashMap<String, String>() {
            {
                put("players", players);
            }
        });
    }

    public void sendInvitation(String playerName, String handlerId, PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendInvitation");
        playerHandler.sendAction(Message.GAME_INVITATION, new HashMap<String, String>() {
            {
                put("playerName", playerName);
                put("handlerId", handlerId);
            }
        });
    }

    public void sendRefuseInvitation(PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendRefuseInvitation");
        playerHandler.sendAction(Message.GAME_INVITATION_RESPONSE,
                new HashMap<String, String>() {
            {
                put("response", "refuse");
            }
        });
    }

    public void sendAcceptInvitation(String gameId, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("@MessageCreator->sendAcceptInvitation");
        HashMap<String, String> messageMap = new HashMap<String, String>() {
            {
                put("response", "accept");
                put("gameId", gameId);
            }
        };
        player1.sendAction(Message.GAME_START, messageMap);
        player2.sendAction(Message.GAME_START, messageMap);
    }

    public void sendGameMoves(String moves, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("@MessageCreator->sendGameMoves");
        HashMap<String, String> messageMap = new HashMap<String, String>() {
            {
                put("response", "accept");
                put("gameMoves", moves);
            }
        };
        player1.sendAction(Message.GAME_START, messageMap);
        player2.sendAction(Message.GAME_START, messageMap);
    }

    public void sendGameEnd(String winnerId, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("@MessageCreator->sendGameEnd, winnerId: " + winnerId);
        HashMap<String, String> messageMap = new HashMap<String, String>() {
            {
                put("winnerId", winnerId);
            }
        };
        player1.sendAction(Message.GAME_END, messageMap);
        player2.sendAction(Message.GAME_END, messageMap);
    }
}
