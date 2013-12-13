package me.nrubin29.core.server.packet.packet;

import java.util.HashMap;
import java.util.Map.Entry;

public abstract class Packet {
	
	HashMap<String, String> args = new HashMap<String, String>();

	public HashMap<String, String> getArgs() {
		return args;
	}
	
	public String asString() {
		StringBuffer buffer = new StringBuffer();
		
		buffer.append(getClass().getSimpleName() + " ");
		
		for (Entry<String, String> arg : args.entrySet()) buffer.append(arg.getKey() + ":" + arg.getValue() + " ");
		
		return buffer.toString();
	}
}