package com.tictactoe.client;

import java.io.IOException;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

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

    public void registerMessageToUI(String uimessage) {
        System.out.println("@registerMessageToUI> msg recieved is > " + uimessage);
        Platform.runLater(() -> {
            Label serverMsg = (Label) App.getScene().lookup("#serverMsg");
//            PasswordField passwordRegister = (PasswordField) App.getScene().lookup("#passwordRegister");
//            PasswordField passwordConfirmRegister = (PasswordField) App.getScene().lookup("#passwordConfirmRegister");
            TextField usernameRegister = (TextField) App.getScene().lookup("#usernameRegister");
            if (uimessage.equals("success")) {
//                serverMsg.setText("Data registered successfully, you can login now!");
//                passwordRegister.setText("");
//                passwordConfirmRegister.setText("");
//                usernameRegister.setText("");
                try {
                    App.setRoot("LoginScene");
                    Label registerMsgLogin = (Label) App.getScene().lookup("#registerMsgLogin");
                    registerMsgLogin.setText("Data registered successfully, you can login now!");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (uimessage.equalsIgnoreCase("duplicated")) {
                serverMsg.setTextFill(Color.RED);
                serverMsg.setText("Username already exists!");
            }
        });

    }

    public void showInvitationPopUp(String PlayerName, String PlayerId) {
        Label playerName = (Label) App.getScene().lookup("#playerNameLabel");
        if (playerName != null) {
            playerName.setText(PlayerName);

        }

    }

}
