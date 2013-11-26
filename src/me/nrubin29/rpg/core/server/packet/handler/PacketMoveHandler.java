package me.nrubin29.rpg.core.server.packet.handler;

import me.nrubin29.rpg.core.entity.Player;
import me.nrubin29.rpg.core.server.Session;
import me.nrubin29.rpg.game.Main;

import java.util.HashMap;

public class PacketMoveHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
		Player player = Session.getInstance().getPlayer(args.get("player"));
		int x = Integer.parseInt(args.get("x")), y = Integer.parseInt(args.get("y"));

        Main.getGUI().getPlayerLabel(player).setLocation(x, y);
    }
}