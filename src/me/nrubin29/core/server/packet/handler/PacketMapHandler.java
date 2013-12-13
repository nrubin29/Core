package me.nrubin29.core.server.packet.handler;

import java.util.HashMap;

import me.nrubin29.core.map.Map;
import me.nrubin29.core.map.MapManager;

public class PacketMapHandler extends PacketHandler {

	public void handle(HashMap<String, String> args) {
        System.out.println("PacketMap!");

		String xml = args.get("xml");
		boolean renderNow = Boolean.valueOf(args.get("renderNow"));
		
		Map map = MapManager.getInstance().createMap(xml);
		
		if (renderNow) map.display();
    }
}