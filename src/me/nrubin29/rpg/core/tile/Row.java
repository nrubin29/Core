package me.nrubin29.rpg.core.tile;

import java.util.ArrayList;

public class Row {

	private ArrayList<Tile> tiles = new ArrayList<Tile>();
	
	public ArrayList<Tile> getTiles() { //TODO: Remove
		return tiles;
	}
	
	public void addTile(Tile t) {
		tiles.add(t);
	}
	
	public Tile tileAt(int i) {
		return tiles.get(i);
	}
}