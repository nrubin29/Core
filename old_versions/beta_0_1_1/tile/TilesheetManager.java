package me.nrubin29.rpg.tile;

import java.util.ArrayList;

public class TilesheetManager {

	private TilesheetManager() { }
	
	private static TilesheetManager instance = new TilesheetManager();
	
	public static TilesheetManager getInstance() {
		return instance;
	}
	
	private ArrayList<Tilesheet> tilesheets = new ArrayList<Tilesheet>();
	
	public void setup() {
		tilesheets.add(new Tilesheet("environment"));
	}
	
	public Tilesheet getTilesheet(String name) {
		for (Tilesheet t : tilesheets) {
			if (t.getName().equalsIgnoreCase(name)) return t;
		}
		return null;
	}
}