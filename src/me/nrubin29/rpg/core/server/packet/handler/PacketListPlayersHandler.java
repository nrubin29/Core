package me.nrubin29.rpg.core.server.packet.handler;

import java.awt.Point;
import java.util.HashMap;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.server.Session;

public class PacketListPlayersHandler extends PacketHandler {

    /*
    players:Noah%20%20,Other%10%10,
     */
	public void handle(HashMap<String, String> args) {
        System.out.println("PacketListPlayers!");

		for (String pl : args.get("players").split(",")) {
            System.out.println("Starting for " + pl);
            boolean addThisPlayer = true;

            for (Player player : Session.getInstance().getPlayers()) {
                if (player.getName().equals(pl)) {
                    addThisPlayer = false;
                    System.out.println(player.getName() + " == " + pl);
                    break;
                }
            }
            
            if (addThisPlayer) {
                String[] plArgs = pl.split("%");
                
                Session.getInstance().addPlayer(new Player(pl, new Point(Integer.parseInt(plArgs[1]), Integer.parseInt(plArgs[2]))));
            }
        }
    }
}