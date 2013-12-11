package me.nrubin29.rpg.tile;

import java.awt.Point;

import javax.swing.ImageIcon;

public enum Tile {

	EMPTY("EMPTY"),
	
	GRASS("environment", 0, 0),
	TREE_TALL_TOP_LEFT("environment", 1, 0, true),
	TREE_TALL_TOP_RIGHT("environment", 2, 0, true),
	
	TREE_TALL_MIDDLE_TOP_LEFT("environment", 1, 1, true),
	TREE_TALL_MIDDLE_TOP_RIGHT("environment", 2, 1, true),
	
	TREE_TALL_MIDDLE_BOTTOM_LEFT("environment", 1, 2, true),
	TREE_TALL_MIDDLE_BOTTOM_RIGHT("environment", 2, 2, true),
	
	TREE_TALL_BOTTOM_LEFT("environment", 1, 3, true),
	TREE_TALL_BOTTOM_RIGHT("environment", 2, 3, true);
	
	private ImageIcon image;
	private boolean isSolid;
	
	Tile(String tilesheet, int x, int y, boolean isSolid) {
		this.image = TilesheetManager.getInstance().getTilesheet(tilesheet).getTile(new Point(x, y));
		this.isSolid = isSolid;
	}
	
	Tile(String tilesheet, int x, int y) {
		this(tilesheet, x, y, false);
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