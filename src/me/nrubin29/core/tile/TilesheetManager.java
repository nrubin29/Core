package me.nrubin29.core.tile;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import me.nrubin29.core.util.ResourceUtil;

public class TilesheetManager {

	private TilesheetManager() { }
	
	private static TilesheetManager instance = new TilesheetManager();
	
	public static TilesheetManager getInstance() {
		return instance;
	}
	
	private ArrayList<Tilesheet> tilesheets = new ArrayList<Tilesheet>();
	
	public void setup() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceUtil.getResourceAsStream("tilesheet/names.txt")));
			
			while (reader.ready()) {
				String file = reader.readLine();
				tilesheets.add(new Tilesheet(file));
			}
			
			reader.close();
		}
		catch (Exception e) { e.printStackTrace(); }
	}
	
	public Tilesheet getTilesheet(String name) {
		for (Tilesheet t : tilesheets) {
			if (t.getName().equalsIgnoreCase(name)) return t;
		}
		return null;
	}
}