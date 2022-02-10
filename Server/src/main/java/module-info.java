/* doesn't work with source level 1.8:
module com.tictactoe.server {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.tictactoe.server to javafx.fxml;
    exports com.tictactoe.server;
}
*/
