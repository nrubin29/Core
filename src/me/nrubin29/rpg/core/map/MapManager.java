package me.nrubin29.rpg.core.map;

import java.util.ArrayList;

public class MapManager {
	
	private MapManager() { }
	
	private static MapManager instance = new MapManager();
	
	public static MapManager getInstance() {
		return instance;
	}
	
	private ArrayList<Map> maps = new ArrayList<Map>();
	
	public void registerMap(Map map) {
		maps.add(map);
	}
	
	public Map getMap(String name) {
		for (Map map : maps) {
			if (map.getName().equalsIgnoreCase(name)) return map;
		}
		
		return null;
	}
}