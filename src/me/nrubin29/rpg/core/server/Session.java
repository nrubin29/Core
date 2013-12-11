package me.nrubin29.rpg.core.server;

import java.util.ArrayList;

import me.nrubin29.rpg.core.entity.Player;

public class Session {

    private Session() { }

    private static Session instance = new Session();

    public static Session getInstance() {
        return instance;
    }

    private ArrayList<Player> players = new ArrayList<Player>();
    private Player localPlayer;

    public Player getPlayer(String name) {
        for (Player player : getPlayers()) {
            if (player.getName().equalsIgnoreCase(name)) return player;
        }

        return null;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> allPlayers = new ArrayList<Player>(players);
        allPlayers.add(localPlayer);
        return allPlayers;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(Player localPlayer) {
        this.localPlayer = localPlayer;
    }
}