package me.nrubin29.rpg.tile;

import java.awt.Point;

import javax.swing.ImageIcon;

public enum Tile {

	EMPTY,
	
	GRASS("environment", "G", 0, 0),
	TREE_TALL_TOP_LEFT("environment", "TTTL", 1, 0, true),
	TREE_TALL_TOP_RIGHT("environment", "TTTR", 2, 0, true),
	
	TREE_TALL_MIDDLE_TOP_LEFT("environment", "TTMTL", 1, 1, true),
	TREE_TALL_MIDDLE_TOP_RIGHT("environment", "TTMTR", 2, 1, true),
	
	TREE_TALL_MIDDLE_BOTTOM_LEFT("environment", "TTMBL", 1, 2, true),
	TREE_TALL_MIDDLE_BOTTOM_RIGHT("environment", "TTMBR", 2, 2, true),
	
	TREE_TALL_BOTTOM_LEFT("environment", "TTBL", 1, 3, true),
	TREE_TALL_BOTTOM_RIGHT("environment", "TTBR", 2, 3, true),
	
	/* */
	
	COFFIN_DOWN("coffin", "CD", 0, 0, true),
	COFFIN_LEFT("coffin", "CL", 1, 0, true),
	COFFIN_RIGHT("coffin", "CR", 0, 1, true),
	COFFIN_UP("coffin", "CU", 1, 1, true);
	
	private String id;
	private ImageIcon image;
	private boolean isSolid;
	
	Tile(String tilesheet, String id, int x, int y, boolean isSolid) {
		this.id = id;
		this.image = TilesheetManager.getInstance().getTilesheet(tilesheet).getTile(new Point(x, y));
		this.isSolid = isSolid;
	}
	
	Tile(String tilesheet, String id, int x, int y) {
		this(tilesheet, id, x, y, false);
	}
	
	Tile() { this.id = "E"; }
	
	public String getID() {
		return id;
	}
	
	public ImageIcon getImage() {
		return image;
	}
	
	public boolean isSolid() {
		return isSolid;
	}

	public static Tile byID(String str) {
		for (Tile t : Tile.values()) {
			if (t.getID().equalsIgnoreCase(str)) return t;
		}
		
		return null;
	}
}