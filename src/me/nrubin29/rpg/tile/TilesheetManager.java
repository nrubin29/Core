package me.nrubin29.rpg.tile;

import java.io.File;
import java.util.ArrayList;

import me.nrubin29.rpg.Main;

public class TilesheetManager {

	private TilesheetManager() { }
	
	private static TilesheetManager instance = new TilesheetManager();
	
	public static TilesheetManager getInstance() {
		return instance;
	}
	
	private ArrayList<Tilesheet> tilesheets = new ArrayList<Tilesheet>();
	
	public void setup() {
		try {
			for (String file : new File(Main.class.getClassLoader().getResource("res/tilesheet/").toURI()).list()) {
				tilesheets.add(new Tilesheet(file));
			}
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