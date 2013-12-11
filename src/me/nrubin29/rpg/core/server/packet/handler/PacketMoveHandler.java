package me.nrubin29.rpg.core.server.packet.handler;

import java.util.HashMap;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.gui.GUI;
import me.nrubin29.rpg.core.map.Direction;
import me.nrubin29.rpg.core.server.Session;

public class PacketMoveHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
        System.out.println("Got PacketMove " + "for " + args.get("player"));

		Player player = Session.getInstance().getPlayer(args.get("player"));
		Direction dir = Direction.valueOf(args.get("direction"));

        GUI.getInstance().movement(player, dir, false); //TODO Maybe not?
    }
}