package com.tictactoe.actions;

import java.util.HashMap;

/**
 *
 *
 */
public class MessageCreator {

    private final ActionController actionController;

    MessageCreator(ActionController actionController) {
        this.actionController = actionController;
    }

    /**
     *
     * @param username
     * @param password
     */
    public void sendLogin(String username, String password) {
        System.out.println("-----\n>>@MessageCreator->sendLogin, username:" + username + ", password:" + password);
        actionController.sendAction(Message.LOGIN, new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
            }
        });
    }

    /**
     *
     * @param username
     * @param password
     * @param confirmPassword
     * @param avatarNumber
     */
    public void sendRegister(String username, String password, String confirmPassword, String avatarNumber) {
        System.out.println("-----\n>>@ClientMessageCreator->sendRegister, username:" + username + ", passwordx2:" + password);
        actionController.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
                put("confirmPassword", confirmPassword);
                put("avatar", avatarNumber);
            }
        });
    }

    public void requestPlayersList() {
        System.out.println("-----\n>>@MessageCreator->requestPlayersList");
        actionController.sendAction(Message.PLAYERS_LIST, new HashMap<>());
    }

    public void sendInvitation(String opponentId) {
        System.out.println("-----\n>>@MessageCreator->sendInvitation, opponentId: " + opponentId);
        actionController.sendAction(Message.GAME_INVITATION, new HashMap<String, String>() {
            {
                put("opponentId", opponentId);
            }
        });
    }

    public void sendInvitationResponse(String response) {// accept | refuse
        System.out.println("-----\n>>@MessageCreator->sendInvitationResponse, response: " + response);
        actionController.sendAction(Message.GAME_INVITATION_RESPONSE, new HashMap<String, String>() {
            {
                put("response", response);
            }
        });
    }

    /**
     *
     * @param index
     */
    public void sendGameMove(String index) {
        System.out.println("-----\n>>@MessageCreator->sendGameMove, index: " + index);
        String gameId = actionController.appManager.getGameId();
        String playWith = actionController.appManager.getPlayWith();
        actionController.sendAction(Message.GAME_MOVE, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("index", index);
                put("playWith", playWith);
            }
        });
    }

    public void sendGameClose(String gameId, String playWith) {
        System.out.println("-----\n>>@MessageCreator->sendGameClose");
        actionController.sendAction(Message.GAME_CLOSE, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("playWith", playWith);
            }
        });
    }
}
