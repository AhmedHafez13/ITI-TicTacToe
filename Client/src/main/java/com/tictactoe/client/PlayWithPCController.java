package com.tictactoe.client;

import java.io.IOException;
import javafx.fxml.FXML;

/**
 *
 * @author moham
 */
public class PlayWithPCController {

    @FXML
    private void switchToEasyGameLevel() throws IOException {
        AppManager.GameLevel = "easy";
        //App.setRoot("Game");
        App.sceneManager.showGameScene(App.appManager.getPlayerData().getName(),
                "Easy PC", "X");
    }

    @FXML
    private void switchToHardGameLevel() throws IOException {
        AppManager.GameLevel = "hard";
        //App.setRoot("Game");
        App.sceneManager.showGameScene(App.appManager.getPlayerData().getName(),
                "Hard PC", "X");
    }

    @FXML
    private void backClick() throws IOException {
        App.sceneManager.showMainMenu(App.appManager.getPlayerData());
    }
}
