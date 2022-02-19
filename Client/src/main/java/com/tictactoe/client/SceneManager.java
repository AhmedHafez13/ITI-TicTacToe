package com.tictactoe.client;

import com.tictactoe.actions.MessageCreator;
import com.tictactoe.models.Player;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 *
 *
 */
public class SceneManager {

    public void showMainMenu(Player playerData) {
        System.out.println("-----\n@SceneManager->showMainMenu, "
                + "switching to the main menu scene");
        Platform.runLater(() -> {
            try {
                App.setRoot("mainPage");
                Label userNameField = (Label) App.getScene().lookup("#userNameField");
                userNameField.setText(playerData.getName() + " | Score: "
                        + playerData.getTotalScore());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void showLoginFailed(String userMessage) {
        Platform.runLater(() -> {
            Label messageLabel = (Label) App.getScene().lookup("#registerMsgLogin");
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

    public void showGameScene(String playerName, String opponentName, boolean startGame) {
        Platform.runLater(() -> {
            try {
                App.setRoot("game");
                for (int i = 0; i < 9; i++) {
                    String btnId = "#btn" + i;
                    Button targetBtn = (Button) App.getScene().lookup(btnId);
                    targetBtn.setDisable(!startGame);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void gameMovesToUI(int[] moves) {
        Platform.runLater(() -> {
            for (int i = 0; i < moves.length; i++) {
                char text = i % 2 == 0 ? 'X' : 'O';

                String btnId = "#btn" + i;
                Button targetBtn = (Button) App.getScene().lookup(btnId);

                targetBtn.setDisable(true);
                targetBtn.setText(text + "");
            }
        });

    }

    public void listPlayers(LinkedList<Player> players) {
        Platform.runLater(() -> {
            VBox playersVBox = (VBox) App.getScene().lookup("#playersVBox");
            if (playersVBox != null) {
                playersVBox.getChildren().clear();
                for (int i = 0; i < players.size(); i++) {
                    createPlayerPane(players.get(i), playersVBox);
                }
            } else {
                System.out.println("-----\n@SceneManager->listPlayers, playersVBox is null");
            }
        });
    }

    void createPlayerPane(Player player, VBox playersVBox) {
        HBox playerPane = new HBox();
        playerPane.setAlignment(Pos.CENTER);
        playerPane.setPrefWidth(150);

        ImageView playerImage = new ImageView();
        //playerImage.getLayoutParams().height = 20;
        playerImage.setImage(new Image(getClass()
                .getResource(createAvatarPath(player.getAvatar())).toExternalForm()));
        playerImage.setFitHeight(40);
        playerImage.setFitWidth(40);

        Label playerName = new Label(player.getName());
        HBox.setHgrow(playerName, Priority.ALWAYS);
        playerName.setMaxWidth(Double.MAX_VALUE);
//************************************invite button********************************************************
        Button inviteButton = new Button("Invite");

        inviteButton.setOnAction((event) -> {
            handleInvite(player.getHandlerId());
        });
        inviteButton.setPrefHeight(34.0);
        inviteButton.setPrefWidth(53.0);
        inviteButton.setStyle("-fx-background-color: none; -fx-font-size: 12; -fx-text-fill: #dedc66; -fx-border-width: 2; -fx-border-color: #dedc66; -fx-border-radius: 15;");
        inviteButton.setText("Invite");
        inviteButton.setVisible(!player.getHandlerId().isEmpty());

        playerPane.getChildren().addAll(playerImage, playerName, inviteButton);
        playerPane.setPrefHeight(61);
        playersVBox.getChildren().add(playerPane);
    }

    void handleInvite(String playerId) {
        System.out.println("-----\n@SceneManager->handleInivte, playerId:" + playerId);
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendInvitation(playerId);
    }

    String createAvatarPath(String avatarNo) {
        return "../../../images/avatar" + avatarNo + ".png";
    }
}
