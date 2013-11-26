package me.nrubin29.rpg.core.server.packet.packet;

public class PacketMove extends Packet {

	public PacketMove(String player, int x, int y) {
		args.put("player", player);
		args.put("x", String.valueOf(x));
		args.put("y", String.valueOf(y));
	}
}