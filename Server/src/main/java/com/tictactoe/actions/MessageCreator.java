package com.tictactoe.actions;

import com.tictactoe.models.Player;
import com.tictactoe.server.PlayerHandler;
import java.util.HashMap;

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
        System.out.println("-----\n>>@MessageCreator->sendLoginFailed, message:" + message);

        HashMap data = new HashMap<String, String>() {
            {
                put("loginResult", "failed");
                put("userMessage", message);
            }
        };
        playerHandler.sendAction(Message.LOGIN, data);
    }

    public void sendLoginSuccess(Player player, PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendLoginSuccess");
        playerHandler.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("loginResult", "success");
                put("handlerId", playerHandler.getHandlerId());
                put("playerName", player.getName());
                put("playerScore", String.valueOf(player.getTotalScore()));
                put("playerAvatar", player.getAvatar());
            }
        });
    }

    public void sendRegisterSuccess(PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendRegisterSuccess");
        playerHandler.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("registerResult", "success");
            }
        });
    }

    public void sendRegisterFailed(String message, PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendRegisterFailed");

        playerHandler.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("registerResult", "duplicated");
            }
        });
    }

    public void sendPlayersList(String players, PlayerHandler playerHandler) {
        //
        System.out.println("-----\n>>@MessageCreator->sendPlayersList");

        playerHandler.sendAction(Message.PLAYERS_LIST, new HashMap<String, String>() {
            {
                put("players", players);
            }
        });
    }

    public void sendInvitation(String playerName, String handlerId, PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendInvitation");
        playerHandler.sendAction(Message.GAME_INVITATION, new HashMap<String, String>() {
            {
                put("playerName", playerName);
                put("handlerId", handlerId);
            }
        });
    }

    public void sendRefuseInvitation(PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendRefuseInvitation");
        playerHandler.sendAction(Message.GAME_INVITATION_RESPONSE,
                new HashMap<String, String>() {
            {
                put("response", "refuse");
            }
        });
    }

    public void sendAcceptInvitation(String gameId, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("-----\n>>@MessageCreator->sendAcceptInvitation");
        player1.sendAction(Message.GAME_START, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("opponentName", player2.getPlayer().getName());
                put("startTheGame", "true");
            }
        });
        player2.sendAction(Message.GAME_START, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("opponentName", player1.getPlayer().getName());
                put("startTheGame", "false");
            }
        });
    }

    public void sendGameMoves(String moves, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("-----\n>>@MessageCreator->sendGameMoves");
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
        System.out.println("-----\n>>@MessageCreator->sendGameEnd, winnerId: " + winnerId);
        HashMap<String, String> messageMap = new HashMap<String, String>() {
            {
                put("winnerId", winnerId);
            }
        };
        player1.sendAction(Message.GAME_END, messageMap);
        player2.sendAction(Message.GAME_END, messageMap);
    }
}
