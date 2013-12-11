package me.nrubin29.rpg.core.server.packet.handler;

import java.util.HashMap;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.util.Constants;

public class PacketJoinHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
        System.out.println("PacketJoin!");

		Player player = new Player(args.get("player"), Constants.START_LOCATION);
		
		Session.getInstance().addPlayer(player);
    }
}