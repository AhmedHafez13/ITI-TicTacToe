package com.tictactoe.actions;

import com.tictactoe.models.LogAction;
import com.tictactoe.models.Player;
import com.tictactoe.server.App;
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

        App.appendActionToLog(new LogAction(">>>Sending Message", "Login failed"));

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

        App.appendActionToLog(new LogAction(">>>Sending Message", "Login success",
                player));

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

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Registration success"));

        playerHandler.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("registerResult", "success");
            }
        });
    }

    public void sendRegisterFailed(String message, PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendRegisterFailed");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Registration failed"));

        playerHandler.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("registerResult", "duplicated");
            }
        });
    }

    public void sendPlayersList(String players, PlayerHandler playerHandler) {
        //
        System.out.println("-----\n>>@MessageCreator->sendPlayersList");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending the players list", playerHandler.getPlayer()));

        playerHandler.sendAction(Message.PLAYERS_LIST, new HashMap<String, String>() {
            {
                put("players", players);
            }
        });
    }

    public void sendInvitation(String playerName, String handlerId, PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendInvitation");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending invitation", playerHandler.getPlayer()));

        playerHandler.sendAction(Message.GAME_INVITATION, new HashMap<String, String>() {
            {
                put("playerName", playerName);
                put("handlerId", handlerId);
            }
        });
    }

    public void sendRefuseInvitation(PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendRefuseInvitation");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending invitation refuse", playerHandler.getPlayer()));

        playerHandler.sendAction(Message.GAME_INVITATION_RESPONSE,
                new HashMap<String, String>() {
            {
                put("response", "refuse");
            }
        });
    }

    public void sendAcceptInvitation(String gameId, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("-----\n>>@MessageCreator->sendAcceptInvitation");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending invitation accept", player1.getPlayer()));

        player1.sendAction(Message.GAME_START, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("opponentName", player2.getPlayer().getName());
                put("playWith", "X");
            }
        });
        player2.sendAction(Message.GAME_START, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("opponentName", player1.getPlayer().getName());
                put("playWith", "O");
            }
        });
    }

    public void sendGameMoves(String moves, boolean isMyTurn, PlayerHandler player) {
        System.out.println("-----\n>>@MessageCreator->sendGameMoves");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending game move", player.getPlayer()));

        HashMap<String, String> messageMap = new HashMap<String, String>() {
            {
                put("gameMoves", moves);
                put("isMyTurn", Boolean.toString(isMyTurn));
            }
        };
        player.sendAction(Message.GAME_MOVE, messageMap);
    }

    public void sendGameEnd(String winnerId, PlayerHandler player1, PlayerHandler player2) {
        System.out.println("-----\n>>@MessageCreator->sendGameEnd, winnerId: " + winnerId);

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending game end", player1.getPlayer()));

        HashMap<String, String> messageMap = new HashMap<String, String>() {
            {
                put("winnerId", winnerId);
            }
        };
        player1.sendAction(Message.GAME_END, messageMap);
        player2.sendAction(Message.GAME_END, messageMap);
    }

    public void sendGameClose(PlayerHandler playerHandler) {
        System.out.println("-----\n>>@MessageCreator->sendGameClose");

        App.appendActionToLog(new LogAction(">>>Sending Message",
                "Sending game close", playerHandler.getPlayer()));

        playerHandler.sendAction(Message.GAME_END, new HashMap<>());
    }
}
