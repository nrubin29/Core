package me.nrubin29.core.server.packet.handler;

import java.util.HashMap;

import me.nrubin29.core.entity.Player;
import me.nrubin29.core.server.Session;
import me.nrubin29.core.util.Constants;

public class PacketJoinHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
        System.out.println("PacketJoin!");

		Player player = new Player(args.get("player"), Constants.START_LOCATION);
		
		Session.getInstance().addPlayer(player);
    }
}