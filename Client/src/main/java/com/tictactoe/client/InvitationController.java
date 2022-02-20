package com.tictactoe.client;
import com.tictactoe.actions.MessageCreator;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author yaraa
 */
public class InvitationController implements Initializable {

    @FXML
    private Label playerNameLabel;

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Refused_invetation(ActionEvent event) {
        System.out.println("-----\n@MainPageController->Refused_invetation is clicked!");
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendInvitationResponse("refuse");
        App.sceneManager.showMainMenu(App.appManager.getPlayerData());
    }

    @FXML
    private void Accept_invetation(ActionEvent event) {
        System.out.println("-----\n@MainPageController->Accept_invetation is clicked!");
        MessageCreator messageCreator = App.appManager.actionController.getMessageCreator();
        messageCreator.sendInvitationResponse("accept");
    }
    
}
