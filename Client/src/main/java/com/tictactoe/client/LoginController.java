package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import com.tictactoe.actions.ActionHandler;
import com.tictactoe.actions.MessageCreator;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class LoginController {
//=============Login Scene Elements=============

    @FXML
    private TextField userNameField;
    @FXML
    private TextField passwordField;
    @FXML
    private Label nameError;
    @FXML
    private Label passwordError;
//=============Register Scene Elements===========
    @FXML
    private TextField usernameRegister;
    @FXML
    private TextField passwordRegister;
    @FXML
    private TextField passwordConfirmRegister;
    @FXML
    private Label usernameErrorRegister;
    @FXML
    private Label passwordErrorRegister;
    @FXML
    private Label serverMsg;
    @FXML
    ToggleGroup pic;
//    @FXML
//    private RadioButton av1;
//    @FXML
//    private RadioButton av2;
//    @FXML
//    private RadioButton av3;
//    @FXML
//    private RadioButton av4;
//    @FXML
//    private RadioButton av5;
//    @FXML
//    private RadioButton av6;
//    @FXML
//    private RadioButton av7;
//===============================================

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
    private AnchorPane root;

    @FXML
    private void login() throws IOException {
        //System.out.println("@LoginController.login is called!");
        if (userNameField.getText().isEmpty()) {
            nameError.setText("Username is required!");
        } else if (passwordField.getText().isEmpty()) {
            passwordError.setText("Password is required!");
        } else {
            System.out.println(userNameField.getText().trim() + ":" + passwordField.getText());
            MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
            messageCreator.sendLogin(userNameField.getText().trim(), passwordField.getText());
        }
        // System.out.println("@LoginController.login is called!");
        // MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();

        // String username = usernameField.getText();
        // messageCreator.sendLogin(username, "pass1");
    }

    @FXML
    private void register() throws IOException {
        System.out.println("@LoginController.register is called!");
        System.out.println(usernameRegister.getText() + ":" + passwordRegister.getText() + ":" + passwordConfirmRegister.getText());
        RadioButton selectedRadioButton = (RadioButton) pic.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
//        System.out.println(toogleGroupValue);

        if (usernameRegister.getText().trim().isEmpty() || usernameRegister.getText().trim().length() < 3) {
            usernameErrorRegister.setText("Username is required and must be more than 3 characters!");

        } else if (!passwordRegister.getText().equals(passwordConfirmRegister.getText())) {
            passwordErrorRegister.setText("Incorrect Password!");

        } else {
            MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
            messageCreator.sendRegister(usernameRegister.getText(), passwordRegister.getText(), passwordConfirmRegister.getText(),toogleGroupValue);
        }
    }

}
