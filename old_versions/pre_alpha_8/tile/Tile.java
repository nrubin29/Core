package me.nrubin29.rpg.tile;

import java.awt.Point;

import javax.swing.ImageIcon;

public enum Tile {

	EMPTY("EMPTY"),
	
	GRASS_SQUARE_TOP_LEFT(0, 0),
	GRASS_SQUARE_TOP(1, 0),
	GRASS_SQUARE_TOP_RIGHT(2, 0),
	TREE_LARGE_TALL_TOP_LEFT(4, 0, true),
	TREE_LARGE_TALL_TOP_RIGHT(5, 0, true),
	
	GRASS_SQUARE_MIDDLE_LEFT(0, 1),
	GRASS_SQUARE_MIDDLE(1, 1),
	GRASS_SQUARE_MIDDLE_RIGHT(2, 1),
	TREE_LARGE_TALL_MIDDLE_2_LEFT(4, 1, true),
	TREE_LARGE_TALL_MIDDLE_2_RIGHT(5, 1, true),
	
	GRASS_SQUARE_BOTTOM_LEFT(0, 2),
	GRASS_SQUARE_BOTTOM(1, 2),
	GRASS_SQUARE_BOTTOM_RIGHT(2, 2),
	TREE_LARGE_TALL_MIDDLE_1_LEFT(4, 2, true),
	TREE_LARGE_TALL_MIDDLE_1_RIGHT(5, 2, true),
	
	TREE_LARGE_TALL_BOTTOM_LEFT(4, 3, true),
	TREE_LARGE_TALL_BOTTOM_RIGHT(5, 3, true);
	
	private ImageIcon image;
	private boolean isSolid;
	
	Tile(int x, int y, boolean isSolid) {
		this.image = TilesheetLoader.tiles.get(new Point(x, y));
		this.isSolid = isSolid;
	}
	
	Tile(int x, int y) {
		this(x, y, false);
	}
	
	Tile(String specialConstructorReservedForEmpty) {
		
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
	public boolean isSolid() {
		return isSolid;
	}
}