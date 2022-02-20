package com.tictactoe.client;

import com.tictactoe.actions.MessageCreator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class GameController {

    ArrayList<Button> boardButtons = new ArrayList<>(); //()constructor  <>templet
    
    String currentPlayerSymbol; 
    boolean isGameOver = false;

    @FXML
    private Label player1Name ;
    @FXML
    private Label player2Name ;
    @FXML
    private Button btn0, btn2, btn1, btn3, btn4, btn5, btn6, btn7, btn8;

    public void initialize() {
        boardButtons.add(btn0);
        boardButtons.add(btn1);
        boardButtons.add(btn2);
        boardButtons.add(btn3);
        boardButtons.add(btn4);
        boardButtons.add(btn5);
        boardButtons.add(btn6);
        boardButtons.add(btn7);
        boardButtons.add(btn8);
        
        currentPlayerSymbol = "X";
    }

    
    private void handleButton(int position) throws IOException {
        if("WithPC".equals(AppManager.GameType)) { // Play with PC
            if(isGameOver || checkWin() != null) {
                return;
            }
            
            Boolean playerMove = placeMove(position, currentPlayerSymbol);
           if(checkWin() != null) {
               isGameOver = true;
               gameResult();
           } else if(playerMove && !isGameOver) {
               computerTurn();
           }
        } else {// Play with someone
            gameMove(position);
        }
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
        handleButton(0);
    }

    @FXML
    private void clickBtn1() throws IOException {
        handleButton(1);
    }

    @FXML
    private void clickBtn2() throws IOException {
        handleButton(2);
    }

    @FXML
    private void clickBtn3() throws IOException {
        handleButton(3);
    }

    @FXML
    private void clickBtn4() throws IOException {
        handleButton(4);
    }

    @FXML
    private void clickBtn5() throws IOException {
        handleButton(5);
    }

    @FXML
    private void clickBtn6() throws IOException {
        handleButton(6);
    }

    @FXML
    private void clickBtn7() throws IOException {
        handleButton(7);
    }

    @FXML
    private void clickBtn8() throws IOException {
        handleButton(8);
    }
    
    /* ------------------- Game With PC ---------------------------- */
    private boolean placeMove(int position, String symbol) {
        
        // TODO: check for valid move.
        if(isValidMove(position)) {
            boardButtons.get(position).setText(symbol);
            return true;
        }
        return false;
    }
    
    private boolean isValidMove(int position) {
        return boardButtons.get(position).getText().equals("");
    }
   
    private void computerTurn() {
        if("easy".equals(AppManager.GameLevel)) {
            RandomMode();
        } else if("hard".equals(AppManager.GameLevel)) {
            bestMove();
            if(checkWin() != null)
                isGameOver = true;
        }
    }
    
    private void RandomMode() {
        // Random Move
        Random rand = new Random();
        int computerMove;
        while(true) {
            computerMove = rand.nextInt(9);
            if(isValidMove(computerMove)) {
                computerFinalMove(computerMove);
                break;
            }
        }
    }
    
    private void computerFinalMove(int computerMove) {
        placeMove(computerMove, "O");
        if(checkWin() != null)
            gameResult();
    }
     
    // AI Functionality
    private void bestMove() {
        int bestScore = -100;
        int move = -1;
        for(int i = 0; i < boardButtons.size(); i++) {
            if(isValidMove(i)) {
                boardButtons.get(i).setText("O");
                int score = minimax(boardButtons,0, false);
                boardButtons.get(i).setText("");
                if(score > bestScore) {
                    bestScore = score;
                    move = i;
                }
            }
        }
//        boardButtons.get(move).setText("O");
        computerFinalMove(move);
    }
    
    private int minimax(ArrayList<Button>board, int depth, boolean isMaximizing) {
        String result = checkWin();
        if (result != null) {
            if (result.equalsIgnoreCase("X")) {
                return -10;
            } else if (result.equalsIgnoreCase("O")) {
                return 10;
            } else if (result.equalsIgnoreCase("Tie")) {
                return 0;
            }
        }
        
        if(isMaximizing) {
            int bestScore = -100;
            for(int i = 0; i < board.size(); i++) {
                if("".equals(board.get(i).getText())) {
                    board.get(i).setText("O");
                    int score = minimax(board, depth+1, false);
                    board.get(i).setText("");
                    bestScore = Math.max(score, bestScore);
                }
            }
            return bestScore;
        } else {
            int bestScore = 100;
            for(int i = 0; i < board.size(); i++) {
                if("".equals(board.get(i).getText())) {
                    board.get(i).setText("X");
                    int score = minimax(board, depth + 1, true);
                    board.get(i).setText("");
                    bestScore = Math.min(score, bestScore);
                }
            }
         return bestScore;
        }
    }
    
    private boolean isGameOver() {
        for(int i = 0; i < boardButtons.size(); i++)
            if(boardButtons.get(i).getText().equals(""))
                return false;
        return true;
    }
    
    
    private String checkWin() {
        String winner = null;
        if(handleCheckWin(0, 1, 2)) {
            winner = boardButtons.get(0).getText();
        } else if(handleCheckWin(3, 4, 5)) {
            winner = boardButtons.get(3).getText();
        } else if(handleCheckWin(6, 7, 8)) {
            winner = boardButtons.get(6).getText();
        } else if(handleCheckWin(0, 3, 6)) {
            winner = boardButtons.get(0).getText();
        } else if(handleCheckWin(1, 4, 7)) {
            winner = boardButtons.get(1).getText();
        } else if(handleCheckWin(2, 5, 8)) {
            winner = boardButtons.get(2).getText();
        } else if(handleCheckWin(0, 4, 8)) {
            winner = boardButtons.get(0).getText();
        } else if(handleCheckWin(2, 4, 6)) {
            winner = boardButtons.get(2).getText();
        }
        
        if(isGameOver() && winner == null)
            return "Tie";
        
        return winner;
    }
    private boolean handleCheckWin(int position1, int position2, int position3) {
            if(
                    boardButtons.get(position1).getText().equals(boardButtons.get(position2).getText())
                 && boardButtons.get(position2).getText().equals(boardButtons.get(position3).getText())
                 && !"".equals(boardButtons.get(position1).getText())
              ) {
                return true;
            }
            return false;
        }

    private void gameResult() {
        if(checkWin().equalsIgnoreCase("X")) {
            System.out.println("Player X wins!");
        } else if(checkWin().equalsIgnoreCase("O")) {
            System.out.println("Player O Wins");
        } else if(isGameOver() && checkWin().equalsIgnoreCase("Tie")) {
            System.out.println("Tie");
        }
    }

}
