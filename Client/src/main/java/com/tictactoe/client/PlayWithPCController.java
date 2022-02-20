/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
        App.setRoot("Game");
    }
    @FXML
    private void switchToHardGameLevel() throws IOException {
        AppManager.GameLevel = "hard";
        App.setRoot("Game");
    }
}
