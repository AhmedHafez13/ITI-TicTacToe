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
    private static boolean isConnected = false;

    public static void initializeDB() {
        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/"
                    + DBConfig.DB_NAME, DBConfig.DB_USER, DBConfig.DB_PASSWORD);
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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("total_score");
                boolean isOnline = rs.getBoolean("is_online");
                String avatar = rs.getString("avatar");

                Player p = new Player(id, name, score, avatar, isOnline);
                players.add(p);
                //stmt.close();
                //connection.close(); 
            }
        } catch (SQLException e) {
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
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int score = rs.getInt("total_score");
                boolean isOnline = rs.getBoolean("is_online");
                String avatar = rs.getString("avatar");
                Player p = new Player(id, name, score, avatar, isOnline);
                players.add(p);
                // stmt.close();
                //connection.close(); 
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        // TODO: select players from `player` table, sorted by score
        // higher score first
        return players;
    }

    /**
     * @author Khloud TODO add description
     * @param playerXId
     * @param playerOId
     * @param winner
     * @param moves
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

            st.close();
        } catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }
        // ------------------------- End Of the code----------------------------------------------//      
    }

    /**
     * @author Amr Register new player function insert new row in the database
     * when a new player register
     * @param name
     * @param password
     * @param avatar
     */
    public static boolean registerNewPlayer(String name, String password, String avatar) {
        boolean flag = false;
        try {
            PreparedStatement pstSelect = connection.prepareStatement("select * from player where name = ? and password = ?");
            pstSelect.setString(1, name);
            pstSelect.setString(2, password);
            ResultSet rs = pstSelect.executeQuery();
            if (!rs.next()) //if the data is not found > insert it
            {
                PreparedStatement pst = connection.prepareStatement("insert into player(name,password,avatar) values(?,?,?)");
                pst.setString(1, name);
                pst.setString(2, password);
                pst.setString(3, avatar);
                pst.executeUpdate();
                pst.close();
                flag = true;
            } else {//data is found and can't be inserted!
                flag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * @author Amr
     * @param name
     * @param password
     * @return the playerId if the input data matches a record in the database
     * else it returns -1 if no record found or can't sign in
     */
    public static Player signInPlayer(String name, String password) {
        Player player = null;
        try {
            PreparedStatement pst = connection.prepareStatement("select * from player where name = ? and password = ?");
            pst.setString(1, name);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) //if the data is found
            {
                boolean isOnline = rs.getBoolean("is_online");
                if (!isOnline) {
                    String playerName = rs.getString("name");
                    int score = rs.getInt("total_score");
                    int id = rs.getInt("id");
                    String avatar = rs.getString("avatar");
                    //===================================
                    player = new Player(id, playerName, score, avatar, isOnline);
                    //===================================
                    PreparedStatement updateStatus = connection.prepareStatement("update player set is_online = 1 where id =" + id);
                    updateStatus.executeUpdate();
                }
            }
            pst.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return player;
    }

    public static void setAllPlayersOffline() {
        try {
            PreparedStatement SetAllPlayersOffline = connection.prepareStatement("update player set is_online = 0");
            SetAllPlayersOffline.executeUpdate();
            SetAllPlayersOffline.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void setPlayerOffline(int playerId) {
        try {
            PreparedStatement SetPlayerOffline = connection.prepareStatement("update player set is_online = 0 where id = ?");
            SetPlayerOffline.setInt(1, playerId);
            SetPlayerOffline.executeUpdate();
            SetPlayerOffline.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public static void updatePlayerScore(int winnerPlayerId, int bonus) {
        try {
            //this will appened the new score to the old score! be careful!
            PreparedStatement updatePlayerScore = connection.prepareStatement("update player set total_score = (total_score*1.05) + ? where id = ?");
            updatePlayerScore.setInt(1, bonus);
            updatePlayerScore.setInt(2, winnerPlayerId);
            updatePlayerScore.executeUpdate();
            updatePlayerScore.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
}
