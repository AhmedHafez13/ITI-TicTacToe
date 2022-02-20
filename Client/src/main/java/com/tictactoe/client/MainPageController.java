package com.tictactoe.client;

import com.tictactoe.actions.MessageCreator;
import com.tictactoe.models.Player;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author yaraa
 */
public class MainPageController implements Initializable {

    @FXML
    private VBox playersVBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //playersVBox.getChildren().get(0).setVisible(false);
        //ArrayList<Player> players = App.appManager.getPlayersList();
        //ArrayList<Player> players = new ArrayList<>();
        //players.add(new Player("pppppp-oooooo", "name1", 123, "avatar1.png"));
        //players.add(new Player("aaaccc-rrrttt", "name2", 123, "avatar2.png"));
        //players
        //for (int i = 0; i < players.size(); i++) {
        //    createPlayerPane(players.get(i));
        //}
        App.sceneManager.listPlayers(App.appManager.getPlayersList());
    }

    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("LoginScene");
    }

    @FXML
    private void switchToLeaderBoard() throws IOException {
        App.setRoot("leaderBoard");
    }

     @FXML
    private void switchToPlayWithPCOptions() throws IOException {
        AppManager.GameType="WithPC";
        App.setRoot("playWithPC");
    }   
    
    @FXML
    private void Refresh() throws IOException {
        /*
         * Try to reconnect
         */
    }

    void createPlayerPane(Player player) {
        HBox playerPane = new HBox();
        playerPane.setAlignment(Pos.CENTER);
        playerPane.setPrefWidth(150);

        ImageView playerImage = new ImageView();
        //playerImage.getLayoutParams().height = 20;
        playerImage.setImage(new Image(getClass()
                .getResource("../../../images/" + player.getAvatar()).toExternalForm()));
        playerImage.setFitHeight(40);
        playerImage.setFitWidth(40);

        Label playerName = new Label(player.getName());
        HBox.setHgrow(playerName, Priority.ALWAYS);
//************************************invite button********************************************************
        Button inviteButton = new Button("Invite");

        inviteButton.setOnAction((event) -> {
            handleInvite(player.getHandlerId());
        });
        inviteButton.setPrefHeight(34.0);
        inviteButton.setPrefWidth(53.0);
        inviteButton.setStyle("-fx-background-color: none; -fx-font-size: 12; -fx-text-fill: #dedc66; -fx-border-width: 2; -fx-border-color: #dedc66; -fx-border-radius: 15;");
        inviteButton.setText("Invite");

        playerPane.getChildren().addAll(playerImage, playerName, inviteButton);
        playerPane.setPrefHeight(61);
        playersVBox.getChildren().add(playerPane);
    }

    void handleInvite(String playerId) {
        System.out.println("-----\n@MainPageController->handleInivte, playerId:" + playerId);
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendInvitation(playerId);
    }

}
