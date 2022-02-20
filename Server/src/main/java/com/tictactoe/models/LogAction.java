package com.tictactoe.models;

/**
 *
 * @author AhmedHafez
 */
public class LogAction {

    private final String location;
    private final String description;
    private Player player = null;
    private String playerName = null;

    public LogAction(String location, String description, Player player) {
        this.location = location;
        this.description = description;
        this.player = player;
        if (player != null) {
            playerName = player.getName();
        }
    }

    public LogAction(String location, String description) {
        this.location = location;
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public String getDescription() {
        return description;
    }

    public Player getPlayer() {
        return player;
    }

    public String getPlayerName() {
        return playerName;
    }

}
