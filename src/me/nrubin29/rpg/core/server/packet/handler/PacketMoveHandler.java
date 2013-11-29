package me.nrubin29.rpg.core.server.packet.handler;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.game.Main;

import java.util.HashMap;

public class PacketMoveHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
        System.out.println("Got PacketMove");

		Player player = Session.getInstance().getPlayer(args.get("player"));
		int key = Integer.parseInt(args.get("key"));

        Main.getGUI().movement(player, key, false);
    }
}