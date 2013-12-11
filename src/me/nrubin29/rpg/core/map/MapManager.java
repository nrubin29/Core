package me.nrubin29.rpg.core.map;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class MapManager {
	
	private MapManager() { }
	
	private static MapManager instance = new MapManager();
	
	public static MapManager getInstance() {
		return instance;
	}
	
	private ArrayList<Map> maps = new ArrayList<Map>();
	
	public Map createMap(File file) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			StringBuffer contents = new StringBuffer();
			
			while (reader.ready()) contents.append(reader.readLine());
			
			reader.close();
			
			Map map = new Map(contents.toString());
			maps.add(map);
			return map;
		}
		
		catch (Exception e) { return null; }
	}
	
	public Map createMap(String xml) {
		Map map = new Map(xml);
		maps.add(map);
		return map;
	}
	
	public Map getMap(String name) {
		for (Map map : maps) {
			if (map.getName().equalsIgnoreCase(name)) return map;
		}
		
		return null;
	}
}