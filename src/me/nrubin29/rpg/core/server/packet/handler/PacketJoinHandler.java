package me.nrubin29.rpg.core.server.packet.handler;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.core.util.Data;
import me.nrubin29.rpg.game.Main;

import java.util.HashMap;

public class PacketJoinHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
        System.out.println("PacketJoin!");

		Player player = new Player(args.get("player"), 5 * Data.TILE_DIM, 5 * Data.TILE_DIM);
		
		Session.getInstance().addPlayer(player);
    }
}