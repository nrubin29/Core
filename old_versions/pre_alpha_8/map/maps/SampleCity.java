package me.nrubin29.rpg.map.maps;

import me.nrubin29.rpg.map.Map;
import me.nrubin29.rpg.map.MapType;
import me.nrubin29.rpg.tile.Row;
import me.nrubin29.rpg.tile.Tile;
import me.nrubin29.rpg.util.Constants;

public class SampleCity extends Map {
	
	public SampleCity() {
		super(MapType.CITY, "Sample City");
		
		super.addRow(new Row(Tile.GRASS_SQUARE_TOP_LEFT, Tile.GRASS_SQUARE_TOP, Tile.GRASS_SQUARE_TOP_RIGHT, Tile.EMPTY, Tile.TREE_LARGE_TALL_TOP_LEFT, Tile.TREE_LARGE_TALL_TOP_RIGHT, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		super.addRow(new Row(Tile.GRASS_SQUARE_MIDDLE_LEFT, Tile.GRASS_SQUARE_MIDDLE, Tile.GRASS_SQUARE_MIDDLE_RIGHT, Tile.EMPTY, Tile.TREE_LARGE_TALL_MIDDLE_2_LEFT, Tile.TREE_LARGE_TALL_MIDDLE_2_RIGHT, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		super.addRow(new Row(Tile.GRASS_SQUARE_MIDDLE_LEFT, Tile.GRASS_SQUARE_MIDDLE, Tile.GRASS_SQUARE_MIDDLE_RIGHT, Tile.EMPTY, Tile.TREE_LARGE_TALL_MIDDLE_1_LEFT, Tile.TREE_LARGE_TALL_MIDDLE_1_RIGHT, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		super.addRow(new Row(Tile.GRASS_SQUARE_BOTTOM_LEFT, Tile.GRASS_SQUARE_BOTTOM, Tile.GRASS_SQUARE_BOTTOM_RIGHT, Tile.EMPTY, Tile.TREE_LARGE_TALL_BOTTOM_LEFT, Tile.TREE_LARGE_TALL_BOTTOM_RIGHT, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
		for (int i = 0; i < Constants.NUM_ROWS - 4; i++) super.addRow(new Row(Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY, Tile.EMPTY));
	}
}