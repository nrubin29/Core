package me.nrubin29.rpg.tile;

import java.awt.Point;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.misc.Image;
import me.nrubin29.rpg.util.ImageUtil;

public enum Tile implements Image {

	EMPTY,
	
	GRASS("environment", "G", 1, 1),
	TREE_TALL_TOP_LEFT("environment", "TTTL", 2, 1, true),
	TREE_TALL_TOP_RIGHT("environment", "TTTR", 3, 1, true),
	
	TREE_TALL_MIDDLE_LEFT("environment", "TTML", 2, 2, true),
	TREE_TALL_MIDDLE_RIGHT("environment", "TTMR", 3, 2, true),
	
	TREE_TALL_BOTTOM_LEFT("environment", "TTBL", 2, 3, true),
	TREE_TALL_BOTTOM_RIGHT("environment", "TTBR", 3, 3, true),
	
	/* */
	
	COFFIN_DOWN("environment", "CD", 4, 1, true),
	COFFIN_LEFT("environment", "CL", 5, 1, true),
	COFFIN_RIGHT("environment", "CR", 4, 2, true),
	COFFIN_UP("environment", "CU", 5, 2, true);
	
	private String id;
	private ImageIcon image;
	private boolean isSolid;
	
	Tile(String tilesheet, String id, int x, int y, boolean isSolid) {
		this.id = id;
		this.image = TilesheetManager.getInstance().getTilesheet(tilesheet).getTile(new Point(x - 1, y - 1));
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
	
	public ImageIcon getImage(int width, int height) {
		return ImageUtil.resizeImage(getImage(), width, height);
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