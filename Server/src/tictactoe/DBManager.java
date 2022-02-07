/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tictactoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import models.Player;

public class DBManager {

    private static Connection connection;
    private boolean isConnected = false;

    private final String DB_NAME = "tictactoe";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = ""; //empty password edited by "amr"!

    DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
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
     * @author Amr 
     * Register new player function
     * insert new row in the database when a new player register
     */
    public static void registerNewPlayer(String name, String password, String avatar) {
        try{
            PreparedStatement pst = connection.prepareStatement("insert into player(name,password,avatar) values(?,?,?)");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, avatar);
            pst.executeUpdate();
            pst.close();
            }
            catch(Exception e){e.printStackTrace();}
    }

    /**
     * @author Amr
     * @return the playerId if the input data matches a record in the database
     * else it returns -1 if no record found or can't sign in
     */
    public static int signInPlayer(String name, String password) {
        int playerId = -1;
        try{
            PreparedStatement pst = con.prepareStatement("select * from player where name = ? and password = ?");
            pst.setString(1, name);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if(rs.next()) //if the data is found
            {
                playerId = rs.getInt(1); // playerId is set to db playerId
            }else{
                playerId = -1; //data is not found
            }
            pst.close();
            }
            catch(Exception e){e.printStackTrace();}
        return playerId;
    }
}
