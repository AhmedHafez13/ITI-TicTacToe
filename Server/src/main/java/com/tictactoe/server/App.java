package com.tictactoe.server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

/**
 *
 */
public class App extends Application {

    private static Scene scene;

    static ServerManager serverManager;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("ServerScene"));
        stage.setTitle("Tic Tac Toe! Server");
        stage.setScene(scene);
        stage.show();

        serverManager.initializePlayersTables();

        // [START] DEV
        App.serverManager.toggleServer();
        Label serverStatusLabel = (Label) scene.lookup("#serverStatusLabel");
        Button startStopButton = (Button) scene.lookup("#startStopButton");
        serverStatusLabel.setText("The server is running");
        startStopButton.setText("STOP");
        // [END] DEV
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Scene getScene() {
        return scene;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        serverManager = new ServerManager();

        launch();
    }

}
