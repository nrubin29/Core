package me.nrubin29.rpg.core.map;

import java.util.ArrayList;

import me.nrubin29.rpg.core.Game;
import me.nrubin29.rpg.core.audio.Music;
import me.nrubin29.rpg.core.tile.Row;
import me.nrubin29.rpg.core.tile.Tile;

public abstract class Map {

	public static final String EMPTY_ROW = "E E E E E E E E E E E E E E E E E E E E";
	
	private MapType type;
	private String name;
	private Music backgroundMusic;
	private ArrayList<Row> rows;
	
	public Map(MapType type, String name, Music backgroundMusic, String... stringRows) {
		this.type = type;
		this.name = name;
		this.backgroundMusic = backgroundMusic;
		this.rows = new ArrayList<Row>();
		
		for (String row : stringRows) {
			Row r = new Row();
			
			for (String str : row.split(" ")) {
				str = str.trim();
				if (!str.equals("")) {
					r.addTile(Tile.byID(str));
				}
			}
			
			rows.add(r);
		}
	}
	
	public final MapType getType() {
		return type;
	}
	
	public final String getName() {
		return name;
	}
	
	public final Music getBackgroundMusic() {
		return backgroundMusic;
	}
	
	public final Row getRow(int row) {
		return rows.get(row);
	}
	
	public final void display() {
		Game.getGUI().renderMap(this);
	}
}