package com.tictactoe.client;

import com.tictactoe.actions.MessageCreator;
import java.io.IOException;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

    ArrayList<Button> boardButtons = new ArrayList<>(); //()constructor  <>templet

    @FXML
    private Label player1Name ;
    @FXML
    private Label player2Name ;
    @FXML
    private Button btn0, btn2, btn1, btn3, btn4, btn5, btn6, btn7, btn8;

    public void initialize() {
        System.out.println("####################################");
        boardButtons.add(btn0);
        boardButtons.add(btn1);
        boardButtons.add(btn2);
        boardButtons.add(btn3);
        boardButtons.add(btn4);
        boardButtons.add(btn5);
        boardButtons.add(btn6);
        boardButtons.add(btn7);
        boardButtons.add(btn8);
    }

    /*  ------------------Send Data-----------------*/
    private void gameMove(int index) throws IOException {
        System.out.println("@GameController.gameMove is called!");
        boardButtons.get(index).setDisable(true);
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendGameMove(index + "");
    }

    /*-------------------------------------------------*/
    @FXML
    private void clickBtn0() throws IOException {
        gameMove(0);
    }

    @FXML
    private void clickBtn1() throws IOException {
        gameMove(1);
    }

    @FXML
    private void clickBtn2() throws IOException {
        gameMove(2);
    }

    @FXML
    private void clickBtn3() throws IOException {
        gameMove(3);
    }

    @FXML
    private void clickBtn4() throws IOException {
        gameMove(4);
    }

    @FXML
    private void clickBtn5() throws IOException {
        gameMove(5);
    }

    @FXML
    private void clickBtn6() throws IOException {
        gameMove(6);
    }

    @FXML
    private void clickBtn7() throws IOException {
        gameMove(7);
    }

    @FXML
    private void clickBtn8() throws IOException {
        gameMove(8);
    }

}
