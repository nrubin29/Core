package me.nrubin29.rpg.core.server.packet.packet;

public class PacketJoin extends Packet {

	public PacketJoin(String player) {
		args.put("player", player);
	}
}