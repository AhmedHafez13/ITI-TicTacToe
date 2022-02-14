package com.tictactoe.actions;

import com.tictactoe.client.AppManager;
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

    AppManager appManager;
    ActionHandler actionHandler;

    private final JSONParser parser = new JSONParser();

    public ActionController(AppManager appManager) {
        this.appManager = appManager;
    }

    public void handleAction(String jsonMessage) {
        Message message = getActionData(jsonMessage);
        String action = message.action;
        HashMap<String, String> data = message.data;

        System.out.println("Handling Action: " + action);

        if (action.equalsIgnoreCase("login")) {
            actionHandler.handleLogin(data);

        } else if (action.equalsIgnoreCase("register")) {
            actionHandler.handleRegister(data);

        } else if (action.equalsIgnoreCase("gameInvitation")) {
            actionHandler.handleGameInvitation(data);

        } else if (action.equalsIgnoreCase("move")) {
            actionHandler.handleMove(data);
        }
    }

    public void sendAction(String action, HashMap<String, String> data) {
        appManager.sendMessage(createActionJson(action, data));
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

    public ActionHandler getActionHandler() {
        return actionHandler;
    }
}
