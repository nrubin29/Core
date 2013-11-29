package me.nrubin29.rpg.core.server;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.game.Main;

import java.util.ArrayList;

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
        Main.getGUI().add(player.getLabel(), Data.SPRITE_LAYER);
    }

    public void removePlayer(Player player) {
        players.remove(player);
        Main.getGUI().remove(player.getLabel());
    }

    public Player getLocalPlayer() {
        return localPlayer;
    }

    public void setLocalPlayer(Player localPlayer) {
        this.localPlayer = localPlayer;
    }
}