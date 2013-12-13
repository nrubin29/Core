package me.nrubin29.core.server.packet.packet;

import me.nrubin29.core.map.Direction;

public class PacketMove extends Packet {

	public PacketMove(String player, Direction d, int x, int y) {
		args.put("player", player);
		args.put("direction", d.name());
        args.put("x", String.valueOf(x));
        args.put("y", String.valueOf(y));
	}
}