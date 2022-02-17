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

    public void sendLoginSuccess(ArrayList<String> playerNames, PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendLoginSuccess");
        playerHandler.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("loginResult", "success");
            }
        });
    }
<<<<<<< HEAD

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
                put("registerResult", "failed");
            }
        });
    }

=======
>>>>>>> 7f211ab (invitationhandle)
    public void sendInvitation(String playerName, String handlerId, PlayerHandler playerHandler) {
        System.out.println("@MessageCreator->sendInvitation");
        playerHandler.sendAction(Message.GAME_INVITATION, new HashMap<String, String>() {
            {
                put("playerName", playerName);
                put("handlerId", handlerId);
            }
        });
    }
}
