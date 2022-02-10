/* doesn't work with source level 1.8:
module com.tictactoe.client {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tictactoe.client to javafx.fxml;
    exports com.tictactoe.client;
}
*/
