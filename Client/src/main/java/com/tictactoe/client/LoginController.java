package com.tictactoe.client;

import com.tictactoe.actions.ActionHandler;
import java.io.IOException;
import javafx.fxml.FXML;

public class LoginController {

    @FXML
    private void switchToSignup() throws IOException {
        App.setRoot("SignupScene");
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
    private void login() throws IOException {
        System.out.println("@LoginController.login is called!");
        //ActionHandler actionHandler = App.appManager.actionController.getActionHandler();
        //actionHandler.sendLogin("user1", "pass1");
    }
}
