package com.tictactoe.actions;

import java.util.HashMap;

/**
 *
 *
 */
public class Message {

    public final static String LOGIN = "LOGIN";
    public final static String REGISTER = "REGISTER";
    public final static String PLAYERS_LIST = "PLAYERS_LIST";
    public final static String GAME_INVITATION = "GAME_INVITATION";
    public final static String GAME_INVITATION_RESPONSE = "GAME_INVITATION_RESPONSE";
    public final static String GAME_MOVE = "GAME_MOVE";
    public final static String GAME_START = "GAME_START";
    public final static String GAME_END = "GAME_END";


    public String action;
    public HashMap<String, String> data;

    public Message(String action, HashMap<String, String> data) {
        this.action = action;
        this.data = data;
    }
}
