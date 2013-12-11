package me.nrubin29.rpg.map;

import java.util.ArrayList;

import me.nrubin29.rpg.tile.Row;

public abstract class Map {

	private MapType type;
	private String name;
	private ArrayList<Row> rows;
	
	public Map(MapType type, String name) {
		this.type = type;
		this.name = name;
		this.rows = new ArrayList<Row>();
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
	
	public void addRow(Row row) {
		rows.add(row);
	}
}