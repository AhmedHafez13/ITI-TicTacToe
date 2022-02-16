package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import com.tictactoe.actions.ActionHandler;
import com.tictactoe.actions.MessageCreator;
import com.tictactoe.client.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {
    
    @FXML
    private Button btn0;
    @FXML
    private Button btn2;
    @FXML
    private Button btn1;
    @FXML
    private Button btn3;
    @FXML
    private Button btn4;
    @FXML
    private Button btn5;
    @FXML
    private Button btn6;
    @FXML
    private Button btn7;
    @FXML
    private Button btn8;
    /*  ------------------Send Data-----------------*/
    @FXML
    private void gameMove(String index , Button btn  ) throws IOException {
        System.out.println("@GameController.gameMove is called!");
        btn.setDisable(true); 
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendGameMove(index);
    }
   /*-------------------------------------------------*/
    
    @FXML
    private void clickBtn0() throws IOException {
        char index='0';
        gameMove(""+index,btn0); 
    }

    @FXML
    private void clickBtn1() throws IOException {
       char index='1';
       gameMove(""+index,btn1); 
    }
    
    @FXML
    private void clickBtn2() throws IOException {
       char index='2';
       gameMove(""+index,btn2); 
    }
    
   
    @FXML
    private void clickBtn3() throws IOException {
       char index='3';
       gameMove(""+index,btn3); 
    }
    
   
    @FXML
    private void clickBtn4() throws IOException {
       char index='4';
       gameMove(""+index,btn4); 
    }
    
  
    @FXML
    private void clickBtn5() throws IOException {
       char index='5';
       gameMove(""+index,btn5); 
    }
    
    
    @FXML
    private void clickBtn6() throws IOException {
       char index='6';
       gameMove(""+index,btn6); 
    }
    
   
    @FXML
    private void clickBtn7() throws IOException {
       char index='7';
       gameMove(""+index,btn7); 
    }
    
  
    @FXML
    private void clickBtn8() throws IOException {
      char index='8';
      gameMove(""+index,btn8);    
    }
    
   
}
