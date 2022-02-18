package com.tictactoe.client;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

/**
 *
 *
 */
public class SceneManager {

    public void showMainMenu() {
        System.out.println("-----\n@SceneManager->showMainMenu, "
                + "switching to the main menu scene");
        /* TODO:
         * Switch to the Main Menu Scene
         */
        Platform.runLater(() -> {
            try {
                App.setRoot("mainPage");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showLoginFailed(String userMessage) {
        Platform.runLater(() -> {
            TextField messageLabel = (TextField) App.getScene().lookup("#registerMsgLogin");
            if (messageLabel != null) {
                messageLabel.setText(userMessage);
            }
        });
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
        Platform.runLater(() -> {
            try {
                App.setRoot("invitation");
                Label playerName = (Label) App.getScene().lookup("#playerNameLabel");
                if (playerName != null) {
                    playerName.setText(PlayerName);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void InvitationMessageToUI(String message) {
        System.out.println("@invitaionMessage> invitation response is > " + message);
        Platform.runLater(() -> {
            try {
                App.setRoot("mainPage");
                //ButtonType OK= new ButtonType("OK"); 
                //Dialog<ButtonType> dialog = new Dialog<ButtonType>();
                //dialog.setTitle("Invitation Response");
                //dialog.getDialogPane().getButtonTypes().add(OK);
                //Optional<ButtonType> result = dialog.showAndWait();
                //dialog.setVisible(false);
                // if(!result.isPresent()){
                // }
                //else if(result.get() == OK){
                //        dialog.close();   
                //  } 
                Label invitationmsg = (Label) App.getScene().lookup("#invitationLabel");
                if (message.equalsIgnoreCase("Accepted")) {
                    // dialog.setContentText("Your invitation is accepted :) ");
                    //dialog.setVisible(true);
                    invitationmsg.setTextFill(Color.WHITE);
                    invitationmsg.setText("Your invitation accepted :)");
                } else if (message.equalsIgnoreCase("Rejected")) {
                    // dialog.setContentText("Your invitation is refused :( ");
                    //dialog.setVisible(true);
                    invitationmsg.setTextFill(Color.RED);
                    invitationmsg.setText("Your invitation rejected :(");
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

}
