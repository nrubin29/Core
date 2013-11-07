package me.nrubin29.rpg.tile;

public class Row {

	private Tile[] tiles;
	
	public Row(Tile... tiles) {
		this.tiles = tiles;
	}
	
	public Tile tileAt(int i) {
		return tiles[i];
	}
}