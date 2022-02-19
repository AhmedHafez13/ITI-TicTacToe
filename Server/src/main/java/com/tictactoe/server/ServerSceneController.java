package com.tictactoe.server;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 *
 */
public class ServerSceneController implements Initializable {

    @FXML
    private Label serverStatusLabel;
    @FXML
    private Button startStopButton;
    @FXML
    private TableView onlinePlayersTable;
    @FXML
    private TableView offlinePlayersTable;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupTable(onlinePlayersTable);
        setupTable(offlinePlayersTable);
    }

    private void setupTable(TableView tableview) {
        TableColumn idColumn = (TableColumn) tableview.getColumns().get(0);
        TableColumn nameColumn = (TableColumn) tableview.getColumns().get(1);
        TableColumn scoreColumn = (TableColumn) tableview.getColumns().get(2);

        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("totalScore"));
    }

    @FXML
    private void stopStartServer() throws IOException {
        System.out.println("-----\n@ServerSceneController->stopStartServer, "
                + "startStopButton clicked");
        boolean isServerRunning = App.serverManager.toggleServer();
        if (isServerRunning) {
            serverStatusLabel.setText("The server is running");
            startStopButton.setText("STOP");
        } else {
            serverStatusLabel.setText("The server is not running");
            startStopButton.setText("START");
        }
    }
}
