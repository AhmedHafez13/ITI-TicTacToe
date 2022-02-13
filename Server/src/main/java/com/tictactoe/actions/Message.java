package com.tictactoe.actions;

import java.util.HashMap;

/**
 *
 * 
 */
public class Message {
    public String action;
    public HashMap<String, String> data;

    public Message(String action, HashMap<String, String> data) {
            this.action = action;
            this.data = data;
    }
}
