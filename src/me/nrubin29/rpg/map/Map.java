package me.nrubin29.rpg.map;

import java.util.ArrayList;

import me.nrubin29.rpg.tile.Row;
import me.nrubin29.rpg.tile.Tile;

public abstract class Map {

	public static final String EMPTY_ROW = "E E E E E E E E E E E E E E E E E E E E";
	
	private MapType type;
	private String name;
	private ArrayList<Row> rows;
	
	public Map(MapType type, String name, String... stringRows) {
		this.type = type;
		this.name = name;
		this.rows = new ArrayList<Row>();
		
		for (String row : stringRows) {
			Row r = new Row();
			
			for (String str : row.split(" ")) {
				str.trim();
				if (!str.equals("")) {
					r.addTile(Tile.byID(str));
				}
			}
			
			rows.add(r);
		}
	}
	
	public MapType getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public Row getRow(int row) {
		return rows.get(row);
	}
}