package com.tictactoe.actions;

import com.tictactoe.models.LogAction;
import com.tictactoe.server.App;
import com.tictactoe.server.DBManager;
import com.tictactoe.server.PlayerHandler;
import com.tictactoe.server.ServerManager;
import java.util.HashMap;
import java.util.Iterator;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 *
 */
public class ActionController {

    ServerManager serverManager;
    ActionHandler actionHandler;
    MessageCreator messageCreator;

    private final static JSONParser parser = new JSONParser();

    public ActionController(ServerManager serverManager) {
        this.serverManager = serverManager;
        actionHandler = new ActionHandler(this);
        messageCreator = new MessageCreator(this);
    }

    public void handleAction(String jsonMessage, PlayerHandler playerHandler) {
        Message message = getActionData(jsonMessage);
        String action = message.action;
        HashMap<String, String> data = message.data;

        System.out.println("@ActionController->handleAction, action: " + action);
        App.appendActionToLog(new LogAction("ActionController", "Handling comming action: "
                + action, playerHandler.getPlayer()));

        if (action.equalsIgnoreCase(Message.LOGIN)) {
            actionHandler.handleLogin(data, playerHandler);

        } else if (action.equalsIgnoreCase(Message.REGISTER)) {
            actionHandler.handleRegister(data, playerHandler);

        } else if (action.equalsIgnoreCase(Message.PLAYERS_LIST)) {
            actionHandler.handlePlayersList(data, playerHandler);

        } else if (action.equalsIgnoreCase(Message.GAME_INVITATION)) {
            actionHandler.handleGameInvitation(data, playerHandler);

        } else if (action.equalsIgnoreCase(Message.GAME_INVITATION_RESPONSE)) {
            actionHandler.handleGameInvitationResponse(data, playerHandler);

        } else if (action.equalsIgnoreCase(Message.GAME_MOVE)) {
            actionHandler.handleMove(data, playerHandler);

        } else if (action.equalsIgnoreCase(Message.GAME_CLOSE)) {
            actionHandler.handleCloseGame(data, playerHandler);

        }
    }

    public static String createActionJson(String action, HashMap<String, String> data) {
        JSONObject actionJson = new JSONObject();

        actionJson.put("action", action);

        JSONObject actionData = new JSONObject(data);
        actionJson.put("data", actionData);

        return actionJson.toJSONString();
    }

    public static Message getActionData(String jsonMsg) {
        try {
            JSONObject jsonObj = (JSONObject) parser.parse(jsonMsg);
            String action = (String) jsonObj.get("action");

            HashMap<String, String> actionData = new HashMap<>();
            JSONObject dataJson = (JSONObject) jsonObj.get("data");

            Iterator<String> iterator = dataJson.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                String value = (String) dataJson.get(key);
                actionData.put(key, value);
            }

            return new Message(action, actionData);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Error parsing json message");
        }
        return null;
    }

    public void onPlayerDisconnect(int playerId) {
        DBManager.setPlayerOffline(playerId);
        actionHandler.broadcastPlayersList();
    }

}
