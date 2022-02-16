package com.tictactoe.client;

import com.tictactoe.actions.ActionController;
import com.tictactoe.actions.ActionHandler;
import com.tictactoe.actions.MessageCreator;
import com.tictactoe.client.App;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class GameController {


    public String btnList;
    
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
    private void gameMove(String index) throws IOException {
        System.out.println("@GameController.gameMove is called!");
        
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        
        messageCreator.sendGameMove(index);
    }
   /*-------------------------------------------------*/
    
    @FXML
    private void clickBtn0() throws IOException {
        char index='0';
        btn0.setDisable(true); 
        System.out.println("index : 0");
        btnList = btnList + index;  
        gameMove(btnList); 
    }

    @FXML
    private void clickBtn1() throws IOException {
       char index='1';
       btn1.setDisable(true);
       System.out.println("index : 1");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
    @FXML
    private void clickBtn2() throws IOException {
       char index='2';
       btn2.setDisable(true);
       System.out.println("index : 2");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
   
    @FXML
    private void clickBtn3() throws IOException {
       char index='3';
       btn3.setDisable(true);
       System.out.println("index : 3");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
   
    @FXML
    private void clickBtn4() throws IOException {
       char index='4';
       btn4.setDisable(true);
       System.out.println("index : 4");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
  
    @FXML
    private void clickBtn5() throws IOException {
       char index='5';
       btn5.setDisable(true);
       System.out.println("index : 5");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
    
    @FXML
    private void clickBtn6() throws IOException {
       char index='6';
       btn6.setDisable(true);
       System.out.println("index : 6");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
   
    @FXML
    private void clickBtn7() throws IOException {
       char index='7';
       btn7.setDisable(true);
       System.out.println("index : 7");
       btnList = btnList + index;  
       gameMove(btnList);
    }
    
  
    @FXML
    private void clickBtn8() throws IOException {
      char index='8';
      btn8.setDisable(true);
       System.out.println("index : 8");
       btnList = btnList + index;  
       gameMove(btnList);   
    }
    
   
}
