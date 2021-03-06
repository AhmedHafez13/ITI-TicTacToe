package com.tictactoe.client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 *
 *
 */
public class App extends Application {

    private static Scene scene;
    private static String sceneName;

    static AppManager appManager;
    public static SceneManager sceneManager;

    @Override
    public void start(Stage stage) throws IOException {
        sceneName = "LoginScene";
        scene = new Scene(loadFXML(sceneName));

        stage.setTitle("Tic Tac Toe!");
        stage.getIcons().add(new Image("https://files.softicons.com/download/game-icons/brain-games-icons-by-quizanswers/png/128x128/Tic-Tac-Toe-Game.png"));
        stage.setScene(scene);
        stage.show();

        //
    }

    static void setRoot(String fxml) throws IOException {
        if (sceneName != fxml) {
            sceneName = fxml;
            scene.setRoot(loadFXML(fxml));
        }
    }

    static Scene getScene() {
        return scene;
    }

    public static SceneManager getSceneManager() {
        return sceneManager;
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        appManager = new AppManager();
        appManager.listenToMessages();

        sceneManager = new SceneManager();

        launch();
    }
}
