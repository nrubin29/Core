package me.nrubin29.rpg.map.maps;

import me.nrubin29.rpg.map.Map;
import me.nrubin29.rpg.map.MapType;
import me.nrubin29.rpg.tile.Row;
import me.nrubin29.rpg.tile.Tile;
import me.nrubin29.rpg.util.Constants;

public class SampleCity extends Map {
	
	public SampleCity() {
		super(MapType.CITY, "Sample City");
		
		super.addRow(new Row(Tile.TREE_TALL_TOP_LEFT, Tile.TREE_TALL_TOP_RIGHT, Tile.EMPTY, Tile.COFFIN_DOWN, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		super.addRow(new Row(Tile.TREE_TALL_MIDDLE_TOP_LEFT, Tile.TREE_TALL_MIDDLE_TOP_RIGHT, Tile.EMPTY, Tile.COFFIN_LEFT, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		super.addRow(new Row(Tile.TREE_TALL_MIDDLE_BOTTOM_LEFT, Tile.TREE_TALL_MIDDLE_BOTTOM_RIGHT, Tile.EMPTY, Tile.COFFIN_RIGHT, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		super.addRow(new Row(Tile.TREE_TALL_BOTTOM_LEFT, Tile.TREE_TALL_BOTTOM_RIGHT, Tile.EMPTY, Tile.COFFIN_DOWN, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		for (int i = 0; i < Constants.NUM_ROWS - 4; i++) super.addRow(new Row(Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
	}
}