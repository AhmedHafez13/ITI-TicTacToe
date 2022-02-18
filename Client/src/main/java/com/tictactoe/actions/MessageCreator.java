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
        System.out.println("@MessageCreator->sendLogin, username:" + username + ", password:" + password);
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
     */
    public void sendRegister(String username, String password, String confirmPassword) {
        System.out.println("@ClientMessageCreator->sendRegister, username:" + username + ", passwordx2:" + password);
        actionController.sendAction(Message.REGISTER, new HashMap<String, String>() {
            {
                put("username", username);
                put("password", password);
                put("confirmPassword", confirmPassword);
            }
        });
    }
    public void sendInvitation(String opponentId){
          actionController.sendAction(Message.GAME_INVITATION, new HashMap<String, String>() {
            {
                put("opponentId",opponentId );
            }
        });
    }
    
    public void sendInvitationResponse(String response) {// accept | refuse
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
        String gameId = actionController.appManager.getGameId();
        actionController.sendAction(Message.GAME_MOVE, new HashMap<String, String>() {
            {
                put("gameId", gameId);
                put("index", index);
            }
        });
    }

}
