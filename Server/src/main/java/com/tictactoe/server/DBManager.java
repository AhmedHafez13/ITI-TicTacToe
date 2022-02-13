package com.tictactoe.server;

import com.mysql.cj.jdbc.Driver;
import com.tictactoe.models.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class DBManager {

    private static Connection connection;
    private boolean isConnected = false;

    private final String DB_NAME = "tictactoe";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = ""; //empty password edited by "amr"!

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
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from player ORDER BY is_online DESC "); //true=1 false=0
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("total_score");
                boolean online = rs.getBoolean("is_online");
                String avatar = rs.getString("avatar");
                Player p = new Player(name, score, avatar);
                players.add(p);
                //stmt.close();
                //connection.close(); 
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

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
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("select * from player ORDER BY total_score DESC ");
            while (rs.next()) {
                String name = rs.getString("name");
                int score = rs.getInt("total_score");
                //boolean online = rs.getBoolean("is_online");
                String avatar = rs.getString("avatar");
                Player p = new Player(name, score, avatar);
                players.add(p);
                // stmt.close();
                //connection.close(); 
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        // TODO: select players from `player` table, sorted by score
        // higher score first
        return players;
    }

    /**
     * @author Khloud TODO add description
     */
    public static void insertNewGame(int playerXId, int playerOId, char winner, String moves) {

        // TODO: insert new game into `game` table
        // -----------------------Strat of the code--------------------------------------------//
        try {
            Statement st = connection.createStatement();
            PreparedStatement pst = connection.prepareStatement("INSERT INTO game (player_x_id, player_o_id, winner, moves) VALUES (?, ?, ?, ?)");
            pst.setString(1, String.valueOf(playerXId));
            pst.setString(2, String.valueOf(playerOId));
            pst.setString(3, String.valueOf(winner));
            pst.setString(4, moves);
            pst.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        // ------------------------- End Of the code----------------------------------------------//      
    }

    /**
     * @author Amr Register new player function insert new row in the database
     * when a new player register
     */
    public static void registerNewPlayer(String name, String password, String avatar) {
        try {
            PreparedStatement pst = connection.prepareStatement("insert into player(name,password,avatar) values(?,?,?)");
            pst.setString(1, name);
            pst.setString(2, password);
            pst.setString(3, avatar);
            pst.executeUpdate();
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @author Amr
     * @return the playerId if the input data matches a record in the database
     * else it returns -1 if no record found or can't sign in
     */
    public static int signInPlayer(String name, String password) {
        int playerId = -1;
        try {
            PreparedStatement pst = connection.prepareStatement("select * from player where name = ? and password = ?");
            pst.setString(1, name);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) //if the data is found
            {
                playerId = rs.getInt(1); // playerId is set to db playerId
            } else {
                playerId = -1; //data is not found
            }
            pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return playerId;
    }
}
