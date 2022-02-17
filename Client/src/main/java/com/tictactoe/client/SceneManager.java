package com.tictactoe.client;
import javafx.scene.Node;
import javafx.scene.control.Label;
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
    
        public void showInvitationPopUp(String PlayerName ,String PlayerId) {
        Label playerName = (Label) App.getScene().lookup("#playerNameLabel");
        if (playerName != null) {
            playerName.setText(PlayerName);
            
        }
      
    }
    
    
}
