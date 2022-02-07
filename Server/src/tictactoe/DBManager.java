/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import models.Player;

public class DBManager {

    private static Connection connection;
    private boolean isConnected = false;

    private final String DB_NAME = "tictactoe";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "password";

    DBManager() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + DB_NAME, DB_USER, DB_PASSWORD);
            isConnected = true;

        } catch (SQLException ex) {
            isConnected = false;
            ex.printStackTrace();
        }
    }

    /**
     * @author Yara
     * @return ... TODO add description
     */
    public static LinkedList<Player> getPlayersSortedByStatus() {
        LinkedList<Player> players = new LinkedList<>();

        // TODO: select players from `player` table, sorted by status
        // (is_online = true) first then (is_online = false)
        return players;
    }

    /**
     * @author Yara
     * @return ... TODO add description
     */
    public static LinkedList<Player> getPlayersSortedByScore() {
        LinkedList<Player> players = new LinkedList<>();

        // TODO: select players from `player` table, sorted by score
        // higher score first
        return players;
    }

    /**
     * @author Khloud
     * TODO add description
     */
    public static void insertNewGame(int playerXId, int playerOId, char winner, String moves) {

        // TODO: insert new game into `game` table
    }

    /**
     * @author Amr TODO add description
     */
    public static void registerNewPlayer(String name, String password, String avatar) {

        // TODO: insert new player, When the user signs up
    }

    /**
     * @author Amr
     * @return ... TODO add description
     */
    public static int signInPlayer(String name, String password) {
        int playerId = -1;

        // TODO: validate name and password...
        // check if the name and password matches any record in the databaes
        // set playerId to the player id from the database or return -1
        // if can't signin
        return playerId;
    }
}
