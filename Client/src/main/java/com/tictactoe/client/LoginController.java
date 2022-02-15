package com.tictactoe.client;

import com.tictactoe.actions.ActionHandler;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label nameError;
    @FXML
    private Label passwordError;


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
        //System.out.println("@LoginController.login is called!");
        if(userNameField.getText().isEmpty()){
            nameError.setText("Username is required!");
        }else if(passwordField.getText().isEmpty()){
            passwordError.setText("Password is required!");
        }else{
            //System.out.println(userNameField.getText().trim()+":"+passwordField.getText());
//            MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
//            messageCreator.sendLogin(...);
//            ActionHandler actionHandler = App.appManager.actionController.getActionHandler();
//            actionHandler.sendLogin(userNameField.getText().trim(),passwordField.getText() );
        }
    }
}
