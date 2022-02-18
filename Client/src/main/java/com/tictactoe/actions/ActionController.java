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
    MessageCreator messageCreator;

    private final JSONParser parser = new JSONParser();

    public ActionController(AppManager appManager) {
        this.appManager = appManager;
        actionHandler = new ActionHandler(this);
        messageCreator = new MessageCreator(this);
    }

    public void handleAction(String jsonMessage) {
        Message message = getActionData(jsonMessage);
        String action = message.action;
        HashMap<String, String> data = message.data;

        System.out.println("Handling Action: " + action);

        if (action.equalsIgnoreCase(Message.LOGIN)) {
            actionHandler.handleLogin(data);

        } else if (action.equalsIgnoreCase(Message.REGISTER)) {
            actionHandler.handleRegister(data);
            
        } else if (action.equalsIgnoreCase(Message.PLAYERS_LIST)) {
            actionHandler.handlePlayersList(data);
            
        } else if (action.equalsIgnoreCase(Message.GAME_INVITATION)) {
            actionHandler.handleGameInvitation(data);
            
        } else if (action.equalsIgnoreCase(Message.GAME_INVITATION_RESPONSE)) {
            actionHandler.handleGameInvitationResponse(data);

        } else if (action.equalsIgnoreCase(Message.GAME_START)) {
            actionHandler.handleGameStart(data);

        } else if (action.equalsIgnoreCase(Message.GAME_MOVE)) {
            actionHandler.handleGameMove(data);

        } else if (action.equalsIgnoreCase(Message.GAME_END)) {
            actionHandler.handleGameEnd(data);

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
        System.out.println("@ActionContoller->getActionData, jsonMsg: " + jsonMsg);
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

    public MessageCreator getMessageCreator() {
        return messageCreator;
    }
}
