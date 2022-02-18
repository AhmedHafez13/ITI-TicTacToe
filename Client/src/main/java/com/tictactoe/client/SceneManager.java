package com.tictactoe.client;

import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    
//        public void showInvitationPopUp(String PlayerName ,String PlayerId) {
//        Label playerName = (Label) App.getScene().lookup("#playerNameLabel");
//        if (playerName != null) {
//            playerName.setText(PlayerName);
//            
//        }
//    }
        
    public void gameMovesToUI(int[] moves) {
            
          for(int i=0 ; i<moves.length ; i++){
             selectBtn(i,moves[i]); 
          }  
    }
    
    public char btnText(int index){
        if(index==0 || index==2 || index==4 || index==6 || index==8)
            return 'X';
        return 'O';
    } 
 
    public void selectBtn(int index,int value){
        char text=btnText(index);
        
        Button btn0 = (Button) App.getScene().lookup("#btn0");             // catch btn0
        Button btn1 = (Button) App.getScene().lookup("#btn1");             // catch btn1
        Button btn2 = (Button) App.getScene().lookup("#btn2");             // catch btn2
        Button btn3 = (Button) App.getScene().lookup("#btn3");             // catch btn3
        Button btn4 = (Button) App.getScene().lookup("#btn4");             // catch btn4
        Button btn5 = (Button) App.getScene().lookup("#btn5");             // catch btn5
        Button btn6 = (Button) App.getScene().lookup("#btn6");             // catch btn6
        Button btn7 = (Button) App.getScene().lookup("#btn7");             // catch btn7
        Button btn8 = (Button) App.getScene().lookup("#btn8");             // catch btn8
        
        switch (value){
            case (0):
                btn0.setDisable(true);
                btn0.setText(text+"");
                break;
            case (1):
                btn1.setDisable(true);
                btn1.setText(text+"");
                break;
            case (2):
               btn2.setDisable(true);
                btn2.setText(text+"");
                break;
            case (3):
                btn3.setDisable(true);
                btn3.setText(text+"");
                break;
            case (4):
                btn4.setDisable(true);
                btn4.setText(text+"");
                break;
            case (5):
                btn5.setDisable(true);
                btn5.setText(text+"");
                break;
            case (6):
                btn6.setDisable(true);
                btn6.setText(text+"");
                break;
            case (7):
                btn7.setDisable(true);
                btn7.setText(text+"");
                break;
            case (8):
                btn8.setDisable(true);
                btn8.setText(text+"");
                break;
        }
    }

    public void showMainMenu(String login_success) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
