package com.tictactoe.client;

import com.tictactoe.models.Player;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LeaderBoardController implements Initializable {

    @FXML
    private TableView leaderBoardTable;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TableColumn rankColumn = (TableColumn) leaderBoardTable.getColumns().get(0);
        TableColumn nameColumn = (TableColumn) leaderBoardTable.getColumns().get(1);
        TableColumn scoreColumn = (TableColumn) leaderBoardTable.getColumns().get(2);

        rankColumn.setCellValueFactory(player -> new ReadOnlyObjectWrapper(
                leaderBoardTable.getItems().indexOf(((CellDataFeatures) player)
                        .getValue()) + 1 + ""));
        rankColumn.setSortable(false);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        scoreColumn.setCellValueFactory(new PropertyValueFactory<>("totalScore"));

        LinkedList<Player> players = (LinkedList) App.appManager.getPlayersList().clone();
        players.add(App.appManager.getPlayerData());

        players.sort((player1, player2)
                -> player1.getTotalScore() > player2.getTotalScore() ? -1 : 1);

        leaderBoardTable.getItems().addAll(players);

    }

    @FXML
    private void backClick() throws IOException {
        // Show main menu
        App.sceneManager.showMainMenu(App.appManager.getPlayerData());
        // List the latest players list
        App.sceneManager.listPlayers(App.appManager.getPlayersList());

    }
}
