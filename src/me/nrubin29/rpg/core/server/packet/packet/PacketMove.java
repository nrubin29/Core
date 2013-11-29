package me.nrubin29.rpg.core.server.packet.packet;

public class PacketMove extends Packet {

	public PacketMove(String player, int key, int x, int y) {
		args.put("player", player);
		args.put("key", String.valueOf(key));
        args.put("x", String.valueOf(x));
        args.put("y", String.valueOf(y));
	}
}