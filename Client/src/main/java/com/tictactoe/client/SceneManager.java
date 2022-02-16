package com.tictactoe.client;

import javafx.scene.Node;
import javafx.scene.control.TextField;

/**
 *
 *
 */
public class SceneManager {

    public void showMainMenu(String test/*ArrayList<Player> players*/) {
        TextField userNameField = (TextField) App.getScene().lookup("#userNameField");
        if (userNameField != null) {
            userNameField.setText(test);
        }
        /* TODO:
         * Show the Main Menu Scene and list all players with their status
         */
    }
}
