package me.nrubin29.rpg.core.tile;

import java.awt.Point;

import javax.swing.ImageIcon;

import me.nrubin29.rpg.core.misc.Image;
import me.nrubin29.rpg.core.misc.Interactable;
import me.nrubin29.rpg.core.util.ImageUtil;

public enum Tile implements Image, Interactable {

	EMPTY,
	
	GRASS("environment", "G", 1, 1),
	TREE_TALL_TOP_LEFT("environment", "TTTL", 2, 1, true),
	TREE_TALL_TOP_RIGHT("environment", "TTTR", 3, 1, true),
    COFFIN_DOWN("environment", "CD", 4, 1, true),
    COFFIN_LEFT("environment", "CL", 5, 1, true),

    WATER_VERTICAL("environment", "WV", 1, 2, true),
	TREE_TALL_MIDDLE_LEFT("environment", "TTML", 2, 2, true),
	TREE_TALL_MIDDLE_RIGHT("environment", "TTMR", 3, 2, true),
    COFFIN_RIGHT("environment", "CR", 4, 2, true),
    COFFIN_UP("environment", "CU", 5, 2, true),

    WATER_HORIZONTAL("environment", "WH", 1, 3, true),
	TREE_TALL_BOTTOM_LEFT("environment", "TTBL", 2, 3, true),
	TREE_TALL_BOTTOM_RIGHT("environment", "TTBR", 3, 3, true),
    FENCE_HORIZONTAL("environment", "FH", 4, 3, true),
    FENCE_VERTICAL("environment", "FV", 5, 3, true);

    //TODO: Implement the rest of the tiles.

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

    public void interact() { /* No need to do anything. */ }
}