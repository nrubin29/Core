package me.nrubin29.rpg.core.server.packet.handler;

import java.util.ArrayList;
import java.util.HashMap;

public class PacketHandlerManager {

	private PacketHandlerManager() { }
	
	private static PacketHandlerManager instance = new PacketHandlerManager();
	
	public static PacketHandlerManager getInstance() {
		return instance;
	}
	
	private ArrayList<PacketHandler> packets = new ArrayList<PacketHandler>();

    public void setup() {
        packets.add(new PacketJoinHandler());
        packets.add(new PacketListPlayersHandler());
        packets.add(new PacketMoveHandler());
    }
	
	/*
	 * PacketMove player:hi x:1 y:2
	 */
	public void handle(String unparsedPacket) {
        System.out.println("Handling " + unparsedPacket);

		try {
			String[] unparsedArgs = unparsedPacket.split(" ");
			
			Class<?> packetClass = Class.forName("me.nrubin29.rpg.core.server.packet.handler." + unparsedArgs[0] + "Handler");
			
			HashMap<String, String> args = new HashMap<String, String>();
			
			for (int i = 1; i < unparsedArgs.length; i++) {
				String[] splitArg = unparsedArgs[i].split(":");
				args.put(splitArg[0], splitArg[1]);
			}
			
			for (PacketHandler packet : packets) {
				if (packet.getClass().equals(packetClass)) {
					packet.handle(args);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}