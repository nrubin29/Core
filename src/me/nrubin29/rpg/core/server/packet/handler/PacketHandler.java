package me.nrubin29.rpg.core.server.packet.handler;

import java.util.HashMap;

public abstract class PacketHandler {

	public abstract void handle(HashMap<String, String> args);
}