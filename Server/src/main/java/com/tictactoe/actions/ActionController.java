package com.tictactoe.actions;

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

    private final JSONParser parser = new JSONParser();

    public ActionController(ServerManager serverManager) {
        this.serverManager = serverManager;
        actionHandler = new ActionHandler(this);
    }

    public void handleAction(String jsonMessage, PlayerHandler playerHandler) {
        Message message = getActionData(jsonMessage);
        String action = message.action;
        HashMap<String, String> data = message.data;

        System.out.println("Handling Action: " + action);

        if (action.equalsIgnoreCase("login")) {
            actionHandler.handleLogin(data, playerHandler);

        } else if (action.equalsIgnoreCase("register")) {
            actionHandler.handleRegister(data, playerHandler);

        } else if (action.equalsIgnoreCase("gameInvitation")) {
            actionHandler.handleGameInvitation(data, playerHandler);

        } else if (action.equalsIgnoreCase("move")) {
            String gameId = playerHandler.getGameId();
            // Check if the player is in game
            if (gameId != null) {
                actionHandler.handleMove(data, playerHandler);
            }
        }
    }

    public String createActionJson(String action, HashMap<String, String> data) {
        JSONObject actionJson = new JSONObject();

        actionJson.put("action", action);

        JSONObject actionData = new JSONObject(data);
        actionJson.put("data", actionData);

        return actionJson.toJSONString();
    }

    public Message getActionData(String jsonMsg) {
        System.out.println("@getActionData, jsonMsg: " + jsonMsg);
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

}